package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.EtatPointage;

@Repository
public interface EtatPointageRepository extends JpaRepository<EtatPointage, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
