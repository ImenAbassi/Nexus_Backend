package com.example.nexus.InitializerDB;

import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.nexus.Entitie.User;
import com.example.nexus.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DefaultUserInitializer {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDefaultUser() {
        // Check if a default user already exists
        Optional<User> existingUser = userRepository.findByCin("12345678"); // Assuming CIN is unique
        if (existingUser.isEmpty()) {
            // Create a default user
            User defaultUser = new User();
            defaultUser.setPrenom("Chedli");
            defaultUser.setNom("Jenhani");
            defaultUser.setCin("12345678");
            defaultUser.setTelPortable1("20410944");
            String rawPassword = "password123";
            String encodedPassword = passwordEncoder.encode(rawPassword);
            defaultUser.setPassword(encodedPassword);
            defaultUser.setDateEntree(LocalDate.now());
            defaultUser.setDateDebutContrat(LocalDate.now());
            defaultUser.setSalaire(0.0);

            // Save the default user to the database
            userRepository.save(defaultUser);
            System.out.println("Default user created successfully!");
        } else {
            System.out.println("Default user already exists.");
        }
    }
}
