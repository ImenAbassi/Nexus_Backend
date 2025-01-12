package com.example.nexus.Services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Avance;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.User;
import com.example.nexus.Repository.AvanceRepository;
import com.example.nexus.Repository.UserRepository;

@Service
public class AvanceService {

    @Autowired
    private AvanceRepository avanceRepository;

    @Autowired
    private UserRepository userRepository;

    public Avance creerDemandeAvance(Long userId, BigDecimal montant) {
        User utilisateur = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
                // Vérifiez que le montant ne dépasse pas 250 dinars
                if (montant.compareTo(new BigDecimal("250")) > 0) {
                    throw new IllegalArgumentException("Le montant ne doit pas dépasser 250 dinars.");
                }
        Avance avance = new Avance();
        avance.setDateDemande(LocalDate.now()); // Date système
        avance.setMontant(montant);
        avance.setEtatDemande(EtatDemande.EN_ATTENTE);; // État initial
        avance.setUser(utilisateur); // Liaison avec l'utilisateur

        return avanceRepository.save(avance);
    }
public Avance trouverParId(Long id) {
        return avanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
    }
/* 
    // Mettre à jour l'état de l'autorisation (acceptée ou refusée)
    public Avance changerEtat(Long id, EtatDemande etat) {
        Avance autorisation = trouverParId(id);
        autorisation.setEtatDemande(etat);
    
        // Vérifiez si l'état est maintenant ACCEPTEE
        if (etat == EtatDemande.VALIDE_PAR_RH) {
            autorisation.setDateCreation(LocalDate.now()); // Mettre à jour la date de création
        }
    
        return avanceRepository.save(autorisation);
    }*/
    

    // Ajoutez d'autres méthodes pour gérer les demandes d'avance si nécessaire
}
