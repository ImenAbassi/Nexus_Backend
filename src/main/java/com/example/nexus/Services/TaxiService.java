package com.example.nexus.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.HeureDepart;
import com.example.nexus.Entitie.Taxi;
import com.example.nexus.Entitie.User;
import com.example.nexus.Repository.HeureDepartRepository;
import com.example.nexus.Repository.TaxiRepository;
import com.example.nexus.Repository.UserRepository;
@Service
public class TaxiService {

    @Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HeureDepartRepository heureDepartRepository; // Ajout du repository

    public Taxi demanderTaxi(Long userId, Long heureDepartId) {
        User utilisateur = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        HeureDepart heureDepart = heureDepartRepository.findById(heureDepartId)
                .orElseThrow(() -> new RuntimeException("Heure de départ non trouvée"));

        Taxi taxi = new Taxi();
        taxi.setUser(utilisateur);
        taxi.setHeureDepart(heureDepart); // Référence à l'heure de départ
        taxi.setEtatDemande(EtatDemande.EN_ATTENTE); // État par défaut
        taxi.setDateCreation(LocalDate.now()); // Affecter la date système

        return taxiRepository.save(taxi);
    }
      // Trouver une autorisation par ID
      public Taxi trouverParId(Long id) {
        return taxiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taxi non trouvée"));
    }

 public Taxi changerEtat(Long id, EtatDemande etat) {
    Taxi autorisation = trouverParId(id);
        autorisation.setEtatDemande(etat);
        return taxiRepository.save(autorisation);
    }

    

}
