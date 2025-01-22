package com.example.nexus.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // Créer une nouvelle autorisation de sortie
    @Transactional
    public AutorisationSortie creerDemande(AutorisationSortie autorisationSortie) {
        if (autorisationSortie.getDateCreation() == null) {
            autorisationSortie.setDateCreation(LocalDate.now());
        }
        return autorisationSortieRepository.save(autorisationSortie);
    }

    // Valider par le superviseur
    @Transactional
    public void validerParSuperviseur(Long idAutorisation, EtatDemande etat) {
        AutorisationSortie autorisation = autorisationSortieRepository.findById(idAutorisation)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
        autorisation.setEtatSuperviseur(etat);
        autorisationSortieRepository.save(autorisation);
    }

    // Valider par le chef de projet
    @Transactional
    public void validerParChefProjet(Long idAutorisation, EtatDemande etat) {
        AutorisationSortie autorisation = autorisationSortieRepository.findById(idAutorisation)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
        autorisation.setEtatChefProjet(etat);
        autorisationSortieRepository.save(autorisation);
    }

    // Valider par les RH
    @Transactional
    public void validerParRH(Long idAutorisation, EtatDemande etat) {
        AutorisationSortie autorisation = autorisationSortieRepository.findById(idAutorisation)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
        autorisation.setEtatRH(etat);
        autorisationSortieRepository.save(autorisation);
    }

    // Récupérer l'historique des validations
    public List<ValidationHistorique> obtenirHistorique(Long idAutorisation) {
        AutorisationSortie autorisation = autorisationSortieRepository.findById(idAutorisation)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
        return new ArrayList<>();
    }

    // Récupérer toutes les autorisations de sortie
    public List<AutorisationSortie> getAllAutorisations() {
        return autorisationSortieRepository.findAll();
    }

    // Récupérer une autorisation de sortie par son ID
    public AutorisationSortie getAutorisationById(Long id) {
        return autorisationSortieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
    }

    // Supprimer une autorisation de sortie
    @Transactional
    public void deleteAutorisation(Long id) {
        autorisationSortieRepository.deleteById(id);
    }

    // Mettre à jour une autorisation de sortie
    @Transactional
    public AutorisationSortie updateAutorisation(Long id, AutorisationSortie autorisationSortie) {
        AutorisationSortie existingAutorisation = autorisationSortieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autorisation non trouvée"));
        existingAutorisation.setDateDebut(autorisationSortie.getDateDebut());
        existingAutorisation.setDateFin(autorisationSortie.getDateFin());
        existingAutorisation.setHeureDebut(autorisationSortie.getHeureDebut());
        existingAutorisation.setHeureFin(autorisationSortie.getHeureFin());
        existingAutorisation.setNbreHeure(autorisationSortie.getNbreHeure());
        existingAutorisation.setRaison(autorisationSortie.getRaison());
        return autorisationSortieRepository.save(existingAutorisation);
    }

    // Récupérer les autorisations en attente de validation par le superviseur
    public List<AutorisationSortie> getAutorisationsEnAttenteSuperviseur() {
        return autorisationSortieRepository.findByEtatSuperviseur(EtatDemande.EN_ATTENTE);
    }

    // Récupérer les autorisations en attente de validation par le chef de projet
    public List<AutorisationSortie> getAutorisationsEnAttenteChefProjet() {
        return autorisationSortieRepository.findByEtatChefProjet(EtatDemande.EN_ATTENTE);
    }

    // Récupérer les autorisations en attente de validation par les RH
    public List<AutorisationSortie> getAutorisationsEnAttenteRH() {
        return autorisationSortieRepository.findByEtatRH(EtatDemande.EN_ATTENTE);
    }

    // Récupérer les autorisations validées
    public List<AutorisationSortie> getAutorisationsValidees() {
        return autorisationSortieRepository.findByEtatSuperviseurAndEtatChefProjetAndEtatRH(
                EtatDemande.APPROUVEE, EtatDemande.APPROUVEE, EtatDemande.APPROUVEE);
    }

    // Récupérer les autorisations refusées
    public List<AutorisationSortie> getAutorisationsRefusees() {
        return autorisationSortieRepository.findByEtatSuperviseurOrEtatChefProjetOrEtatRH(
                EtatDemande.REJETEE, EtatDemande.REJETEE, EtatDemande.REJETEE);
    }
}