package com.example.nexus.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Langue;

@Repository
public interface LangueRepository extends JpaRepository<Langue, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire.
}
