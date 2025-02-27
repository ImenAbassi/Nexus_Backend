package com.example.nexus.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nexus.Entitie.DemandeConge;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Repository.DemandeCongeRepository;
import com.example.nexus.Repository.UserCompagneRepository;

@Service
public class DemandeCongeService {

    private static final Logger logger = LoggerFactory.getLogger(DemandeCongeService.class);

    @Autowired
    private DemandeCongeRepository demandeCongeRepository;

    @Autowired
    private UserCompagneRepository userCompagneRepository;

    public List<DemandeConge> findAll() {
        return demandeCongeRepository.findAll();
    }

    public Optional<DemandeConge> findById(Long id) {
        return demandeCongeRepository.findById(id);
    }

    @Transactional
    public DemandeConge creerDemande(DemandeConge demandeConge) {
        logger.debug("Création de la demande de congé : {}", demandeConge);
        
        if (demandeConge.getDateCreation() == null) {
            demandeConge.setDateCreation(LocalDate.now());
        }
        
        if (demandeConge.getUser() == null) {
            logger.error("L'utilisateur de la demande de congé est null !");
        } else {
            logger.debug("Utilisateur de la demande de congé : {}", demandeConge.getUser().getIdUser());
        }

        return demandeCongeRepository.save(demandeConge);
    }

    public void deleteById(Long id) {
        demandeCongeRepository.deleteById(id);
    }

    @Transactional
    public void validerParSuperviseur(Long idConge, EtatDemande etat) {
        DemandeConge conge = demandeCongeRepository.findById(idConge)
                .orElseThrow(() -> new RuntimeException("Demande de congé non trouvée"));
        conge.validerParSuperviseur(etat);
        demandeCongeRepository.save(conge);
    }

    @Transactional
    public void validerParChefProjet(Long idConge, EtatDemande etat) {
        DemandeConge conge = demandeCongeRepository.findById(idConge)
                .orElseThrow(() -> new RuntimeException("Demande de congé non trouvée"));
        conge.validerParChefProjet(etat);
        demandeCongeRepository.save(conge);
    }

    @Transactional
    public void validerParRH(Long idConge, EtatDemande etat) {
        DemandeConge conge = demandeCongeRepository.findById(idConge)
                .orElseThrow(() -> new RuntimeException("Demande de congé non trouvée"));
        conge.validerParRH(etat);
        demandeCongeRepository.save(conge);
    }

       public List<DemandeConge> getDemandesForSupervisor(User supervisor) {
        // Step 1: Find all UserCompagne records where the supervisor is the given user
        List<UserCompagne> userCompagnes = userCompagneRepository.findBySupervisor(supervisor);

        // Step 2: Extract the users from the UserCompagne records
        List<User> supervisedUsers = new ArrayList<>();
        for (UserCompagne userCompagne : userCompagnes) {
            supervisedUsers.add(userCompagne.getUser());
        }

        // Step 3: Fetch all DemandeConge records for the supervised users
        List<DemandeConge> demandes = new ArrayList<>();
        for (User user : supervisedUsers) {
            demandes.addAll(demandeCongeRepository.findByUser(user));
        }

        return demandes;
    }

    // Get all leave requests for users where the given user is their project leader
    public List<DemandeConge> getDemandesForProjectLeader(User projectLeader) {
        // Step 1: Find all UserCompagne records where the project leader is the given user
        List<UserCompagne> userCompagnes = userCompagneRepository.findByProjectLeader(projectLeader);

        // Step 2: Extract the users from the UserCompagne records
        List<User> projectUsers = new ArrayList<>();
        for (UserCompagne userCompagne : userCompagnes) {
            projectUsers.add(userCompagne.getUser());
        }

        // Step 3: Fetch all DemandeConge records for the project users
        List<DemandeConge> demandes = new ArrayList<>();
        for (User user : projectUsers) {
            demandes.addAll(demandeCongeRepository.findByUser(user));
        }

        return demandes;
    }

    public List<DemandeConge> getDemandesByUser(User user) {
        return demandeCongeRepository.findByUser(user);
    }

}
