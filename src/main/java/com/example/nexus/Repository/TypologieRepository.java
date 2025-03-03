package com.example.nexus.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Typologie;

@Repository
public interface TypologieRepository extends JpaRepository<Typologie, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire.
}
