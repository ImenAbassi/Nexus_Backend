package com.example.nexus.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.AutorisationSortie;
import com.example.nexus.Entitie.EtatDemande;

@Repository
public interface AutorisationSortieRepository extends JpaRepository<AutorisationSortie, Long> {
    @SuppressWarnings("null")
    Optional<AutorisationSortie> findById(Long id);
    List<AutorisationSortie> findByEtatSuperviseur(EtatDemande etat);
    List<AutorisationSortie> findByEtatChefProjet(EtatDemande etat);
    List<AutorisationSortie> findByEtatRH(EtatDemande etat);
    List<AutorisationSortie> findByEtatSuperviseurAndEtatChefProjetAndEtatRH(EtatDemande etatSuperviseur, EtatDemande etatChefProjet, EtatDemande etatRH);
    List<AutorisationSortie> findByEtatSuperviseurOrEtatChefProjetOrEtatRH(EtatDemande etatSuperviseur, EtatDemande etatChefProjet, EtatDemande etatRH);
}