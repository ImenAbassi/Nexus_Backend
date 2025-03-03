package com.example.nexus.Repository;


import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.Taxi;
import com.example.nexus.Entitie.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {
     // Find all taxis by user ID
    List<Taxi> findByUserIdUser(Long userId);

    // Find all taxis by state (EtatDemande)
    List<Taxi> findByEtatDemande(EtatDemande etatDemande);

    // Find all taxis created on a specific date
    List<Taxi> findByDateCreation(LocalDate dateCreation);

    // Find all taxis with a specific arrival location
    List<Taxi> findByLocalisationArrivee(String localisationArrivee);

    // Find all taxis with a specific departure time
    List<Taxi> findByHeureDepart(String heureDepart);

     // Récupérer toutes les demandes de taxi pour un utilisateur spécifique
     List<Taxi> findByUser(User user);

     // Récupérer une demande de taxi par son ID
     Optional<Taxi> findById(Long id);
}
