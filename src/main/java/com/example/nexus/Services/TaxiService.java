package com.example.nexus.Services;

import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.Taxi;
import com.example.nexus.Entitie.User;
import com.example.nexus.Repository.TaxiRepository;
import com.example.nexus.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaxiService {

    @Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private UserRepository userRepository;

    // Créer une demande de taxi
    public Taxi demanderTaxi(Long userId, String localisationArrivee, String heureDepart) {
        User utilisateur = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Taxi taxi = new Taxi();
        taxi.setUser(utilisateur);
        taxi.setLocalisationArrivee(localisationArrivee);
        taxi.setHeureDepart(heureDepart);
        taxi.setEtatDemande(EtatDemande.EN_ATTENTE); // État par défaut
        taxi.setDateCreation(LocalDate.now()); // Date de création

        return taxiRepository.save(taxi);
    }

    // Récupérer toutes les demandes de taxi
    public List<Taxi> obtenirTousLesTaxis() {
        return taxiRepository.findAll();
    }

    // Récupérer une demande de taxi par son ID
    public Taxi obtenirTaxiParId(Long id) {
        return taxiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taxi non trouvé"));
    }

    // Récupérer les demandes de taxi par utilisateur
    public List<Taxi> obtenirTaxisParUtilisateur(Long userId) {
        return taxiRepository.findByUserIdUser(userId);
    }

    // Mettre à jour l'état d'une demande de taxi
    public Taxi changerEtatTaxi(Long id, EtatDemande etat) {
        Taxi taxi = obtenirTaxiParId(id);
        taxi.setEtatDemande(etat);
        return taxiRepository.save(taxi);
    }

    // Supprimer une demande de taxi
    public void supprimerTaxi(Long id) {
        Taxi taxi = obtenirTaxiParId(id);
        taxiRepository.delete(taxi);
    }

    public List<Taxi> getTaxisByUser(User user) {
        return taxiRepository.findByUser(user);
    }

    // Valider une demande de taxi
    public Taxi validateDemande(Long id, EtatDemande etatDemande) {
        Optional<Taxi> optionalTaxi = taxiRepository.findById(id);
        if (optionalTaxi.isPresent()) {
            Taxi taxi = optionalTaxi.get();
            taxi.setEtatDemande(etatDemande);
            return taxiRepository.save(taxi);
        } else {
            throw new RuntimeException("Demande de taxi non trouvée avec l'ID : " + id);
        }
    }
}