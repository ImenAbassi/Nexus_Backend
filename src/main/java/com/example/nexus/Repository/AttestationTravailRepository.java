package com.example.nexus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.AttestationTravail;
import com.example.nexus.Entitie.EtatDemande;

@Repository
public interface AttestationTravailRepository extends JpaRepository<AttestationTravail, Long> {

    @Query("SELECT COUNT(a) FROM AttestationTravail a WHERE a.etatDemande = :etatDemande AND YEAR(a.dateCreation) = :year")
    long countByEtatDemandeAndDateCreationYear(EtatDemande etatDemande, int year);

    // Rechercher des attestations par utilisateur
    List<AttestationTravail> findByUtilisateurIdUser(Long idUser);
}
