package com.example.nexus.Repository;


import com.example.nexus.Entitie.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
}
