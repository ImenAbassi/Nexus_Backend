package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.JourSemaine;

@Repository
public interface JourSemaineRepository extends JpaRepository<JourSemaine, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
