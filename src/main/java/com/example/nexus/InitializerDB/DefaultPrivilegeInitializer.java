package com.example.nexus.InitializerDB;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.nexus.Entitie.Privilege;
import com.example.nexus.Repository.PrivilegeRepository;

@Component
public class DefaultPrivilegeInitializer {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    // Liste des privilèges à initialiser
    private static final List<String> DEFAULT_PRIVILEGES = Arrays.asList(
        "Validation_Conge_RH",
        "Validation_Conge_ChefProjet",
        "Validation_Conge_Superviseur",
        "All_Parametrage",
        "Validation_Autorisation_RH",
        "Validation_Autorisation_ChefProjet",
        "Validation_Autorisation_Superviseur",
        "Validation_Attestation",
        "Validation_Transport"
    );

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDefaultPrivileges() {
        for (String privilegeName : DEFAULT_PRIVILEGES) {
            // Vérifier si le privilège existe déjà
            Optional<Privilege> existingPrivilege = privilegeRepository.findByName(privilegeName);
            if (existingPrivilege.isEmpty()) {
                // Créer un nouveau privilège
                Privilege privilege = new Privilege();
                privilege.setName(privilegeName);

                // Sauvegarder le privilège dans la base de données
                privilegeRepository.save(privilege);
                System.out.println("Privilege created: " + privilegeName);
            } else {
                System.out.println("Privilege already exists: " + privilegeName);
            }
        }
        System.out.println("Default privileges initialization completed.");
    }
}
