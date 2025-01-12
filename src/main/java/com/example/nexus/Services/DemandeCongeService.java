package com.example.nexus.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nexus.Entitie.DemandeConge;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Repository.DemandeCongeRepository;

@Service
public class DemandeCongeService {

    private static final Logger logger = LoggerFactory.getLogger(DemandeCongeService.class);

    @Autowired
    private DemandeCongeRepository demandeCongeRepository;

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


}
