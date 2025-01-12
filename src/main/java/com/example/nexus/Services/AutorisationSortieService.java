package com.example.nexus.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.AutorisationSortie;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.ValidationHistorique;
import com.example.nexus.Repository.AutorisationSortieRepository;

import jakarta.transaction.Transactional;

@Service
public class AutorisationSortieService {

    @Autowired
    private AutorisationSortieRepository autorisationSortieRepository;

    @Transactional
    public AutorisationSortie creerDemande(AutorisationSortie autorisationSortie) {
        if (autorisationSortie.getDateCreation() == null) {
            autorisationSortie.setDateCreation(LocalDate.now());
        }
        return autorisationSortieRepository.save(autorisationSortie);
    }

    @Transactional
    public void validerParSuperviseur(Long idAutorisation, EtatDemande etat) {
        AutorisationSortie autorisation = autorisationSortieRepository.findById(idAutorisation)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
        // Ajouter la vérification du rôle ici
        autorisation.validerParSuperviseur(etat);
        autorisationSortieRepository.save(autorisation);
    }

    @Transactional
    public void validerParChefProjet(Long idAutorisation, EtatDemande etat) {
        AutorisationSortie autorisation = autorisationSortieRepository.findById(idAutorisation)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
        // Ajouter la vérification du rôle ici
        autorisation.validerParChefProjet(etat);
        autorisationSortieRepository.save(autorisation);
    }

   

    public List<ValidationHistorique> obtenirHistorique(Long idAutorisation) {
        AutorisationSortie autorisation = autorisationSortieRepository.findById(idAutorisation)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
        return autorisation.getHistorique();
    }
}
