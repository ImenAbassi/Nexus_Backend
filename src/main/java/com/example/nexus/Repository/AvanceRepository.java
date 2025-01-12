package com.example.nexus.Repository;

import com.example.nexus.Entitie.Avance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvanceRepository extends JpaRepository<Avance, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
}
