package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Pays;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire.
}