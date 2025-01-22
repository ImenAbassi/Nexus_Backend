package com.example.nexus.Services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.AttestationTravail;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.TypeAttestation;
import com.example.nexus.Entitie.User;
import com.example.nexus.Repository.AttestationTravailRepository;
import com.example.nexus.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AttestationTravailService {

    @Autowired
    private AttestationTravailRepository attestationTravailRepository;

    @Autowired
    private UserRepository userRepository;

    // Créer une nouvelle attestation de travail
    @Transactional
    public AttestationTravail creerAttestation(AttestationTravail attestation) {

        // Valider la date souhaitée
        List<LocalDate> datesValides = getDatesValides(attestation.getTypeAttestation());
        attestation.setDateCreation(LocalDate.now());

        return attestationTravailRepository.save(attestation);
    }

    // Récupérer une attestation par son ID
    public AttestationTravail obtenirAttestationParId(Long id) {
        return attestationTravailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attestation non trouvée"));
    }

    // Mettre à jour l'état d'une attestation de travail
    @Transactional
    public AttestationTravail mettreAJourEtat(Long attestationId, EtatDemande nouvelEtat) {
        AttestationTravail attestation = attestationTravailRepository.findById(attestationId)
                .orElseThrow(() -> new RuntimeException("Attestation non trouvée"));

        if (nouvelEtat == EtatDemande.APPROUVEE && attestation.getReference() == null) {
            int currentYear = LocalDate.now().getYear();
            long count = attestationTravailRepository.countByEtatDemandeAndDateCreationYear(EtatDemande.APPROUVEE,
                    currentYear);
            String reference = String.format("%d/%03d", currentYear, count + 1);
            attestation.setReference(reference);
        }

        attestation.setEtatDemande(nouvelEtat);
        return attestationTravailRepository.save(attestation);
    }

    // Supprimer une attestation de travail
    @Transactional
    public void supprimerAttestation(Long id) {
        attestationTravailRepository.deleteById(id);
    }

    // Récupérer les attestations d'un utilisateur
    public List<AttestationTravail> listerParUtilisateur(Long userId) {
        return attestationTravailRepository.findByUtilisateurIdUser(userId);
    }

    // Récupérer toutes les attestations
    public List<AttestationTravail> listerToutesLesAttestations() {
        return attestationTravailRepository.findAll();
    }

    // Méthode pour obtenir les dates valides en fonction du type d'attestation
    private List<LocalDate> getDatesValides(TypeAttestation type) {
        LocalDate today = LocalDate.now();
        LocalDate oneYearFromNow = today.plusYears(1);
        List<LocalDate> fridays = new ArrayList<>();

        LocalDate currentDate = today.withDayOfMonth(1);
        while (currentDate.isBefore(oneYearFromNow)) {
            if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }

        return fridays;
    }
}