package com.example.nexus.Repository;

import com.example.nexus.Entitie.HeureDepart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeureDepartRepository extends JpaRepository<HeureDepart, Long> {
    // Méthodes spécifiques si nécessaire
}
