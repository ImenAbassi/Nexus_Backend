package com.example.nexus.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.User;
import com.example.nexus.Repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;  // Injecter le PasswordEncoder

    @Autowired
    private UserRepository userRepository;  // Repository pour interagir avec la base de données (remplacer par votre propre repository)

    public boolean authenticate(String cin, String providedPassword) {
        // Rechercher l'utilisateur dans la base de données par son CIN
        Optional<User> userOptional = userRepository.findByCin(cin);

        // Vérifier si l'utilisateur existe
        if (userOptional.isEmpty()) {
            // Si l'utilisateur n'existe pas
            return false;
        }

        // Extraire l'utilisateur de l'Optional
        User user = userOptional.get();  // Ici, on accède à l'objet User

        // Comparer le mot de passe fourni avec celui stocké en base (haché)
        return passwordEncoder.matches(providedPassword, user.getPassword());
    }
}
