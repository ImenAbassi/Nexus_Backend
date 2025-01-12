package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nexus.Entitie.EtatUser;

public interface EtatUserRepository extends JpaRepository<EtatUser, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire, par exemple :
    // List<EtatUser> findByUserId(Long userId);
}
