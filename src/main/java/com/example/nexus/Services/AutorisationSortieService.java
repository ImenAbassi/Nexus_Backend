package com.example.nexus.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.AutorisationSortie;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Entitie.ValidationHistorique;
import com.example.nexus.Repository.AutorisationSortieRepository;
import com.example.nexus.Repository.UserCompagneRepository;

import jakarta.transaction.Transactional;

@Service
public class AutorisationSortieService {

    @Autowired
    private AutorisationSortieRepository autorisationSortieRepository;
    @Autowired
    private UserCompagneRepository userCompagneRepository;

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

    // Récupérer toutes les autorisations de sortie pour un utilisateur spécifique
    public List<AutorisationSortie> getAutorisationsByUser(User utilisateur) {
        return autorisationSortieRepository.findByUtilisateur(utilisateur);
    }

    // Récupérer toutes les autorisations de sortie pour un superviseur spécifique
    public List<AutorisationSortie> getAutorisationsForSupervisor(User supervisor) {
      // Step 1: Find all UserCompagne records where the supervisor is the given user
      List<UserCompagne> userCompagnes = userCompagneRepository.findBySupervisor(supervisor);

      // Step 2: Extract the users from the UserCompagne records
      List<User> supervisedUsers = new ArrayList<>();
      for (UserCompagne userCompagne : userCompagnes) {
          supervisedUsers.add(userCompagne.getUser());
      }

      // Step 3: Fetch all AutorisationSortie records for the supervised users
      List<AutorisationSortie> demandes = new ArrayList<>();
      for (User user : supervisedUsers) {
          demandes.addAll(autorisationSortieRepository.findByUtilisateur(user));
      }

      return demandes; 
      
    }

    // Récupérer toutes les autorisations de sortie pour un chef de projet spécifique
    public List<AutorisationSortie> getAutorisationsForProjectLeader(User projectLeader) {
       // Step 1: Find all UserCompagne records where the project leader is the given user
       List<UserCompagne> userCompagnes = userCompagneRepository.findByProjectLeader(projectLeader);

       // Step 2: Extract the users from the UserCompagne records
       List<User> projectUsers = new ArrayList<>();
       for (UserCompagne userCompagne : userCompagnes) {
           projectUsers.add(userCompagne.getUser());
       }

       // Step 3: Fetch all AutorisationSortie records for the project users
       List<AutorisationSortie> demandes = new ArrayList<>();
       for (User user : projectUsers) {
           demandes.addAll(autorisationSortieRepository.findByUtilisateur(user));
       }

       return demandes;    }
}