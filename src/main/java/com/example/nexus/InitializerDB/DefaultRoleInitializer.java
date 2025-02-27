package com.example.nexus.InitializerDB;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.nexus.Entitie.Privilege;
import com.example.nexus.Entitie.Role;
import com.example.nexus.Repository.PrivilegeRepository;
import com.example.nexus.Repository.RoleRepository;

@Component
public class DefaultRoleInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDefaultRoles() {
        // Vérifier si le rôle "SuperAdmin" existe déjà
        Optional<Role> existingRole = roleRepository.findByName("SuperAdmin");

        if (existingRole.isEmpty()) {
            // Créer un nouveau rôle "SuperAdmin"
            Role role = new Role();
            role.setName("SuperAdmin");
            role.setDescription("SuperAdmin");

            // Récupérer tous les privilèges disponibles
            List<Privilege> allPrivileges = privilegeRepository.findAll();
            role.setPrivileges(allPrivileges);

            // Sauvegarder le rôle dans la base de données
            roleRepository.save(role);
            System.out.println("Role created: SuperAdmin");
        } else {
            System.out.println("Role already exists: SuperAdmin");

            // Récupérer le rôle existant
            Role superAdminRole = existingRole.get();

            // Récupérer tous les privilèges disponibles
            List<Privilege> allPrivileges = privilegeRepository.findAll();

            // Convertir les privilèges du rôle en un set pour faciliter la comparaison
            Set<String> rolePrivilegeNames = superAdminRole.getPrivileges().stream()
                    .map(Privilege::getName)
                    .collect(Collectors.toSet());

            // Identifier les privilèges manquants
            List<Privilege> missingPrivileges = allPrivileges.stream()
                    .filter(privilege -> !rolePrivilegeNames.contains(privilege.getName()))
                    .toList();

            if (!missingPrivileges.isEmpty()) {
                // Ajouter les privilèges manquants au rôle
                superAdminRole.getPrivileges().addAll(missingPrivileges);
                roleRepository.save(superAdminRole);

                System.out.println("Added missing privileges to SuperAdmin: " 
                        + missingPrivileges.stream().map(Privilege::getName).collect(Collectors.joining(", ")));
            } else {
                System.out.println("All privileges are already assigned to SuperAdmin.");
            }
        }

        System.out.println("Default roles initialization completed.");
    }
}