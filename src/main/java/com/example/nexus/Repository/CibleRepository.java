package com.example.nexus.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nexus.Entitie.Cible;

public interface CibleRepository extends JpaRepository<Cible, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire, par exemple :
    // List<Cible> findByType(String type);
}
