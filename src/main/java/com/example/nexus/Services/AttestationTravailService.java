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
import com.example.nexus.Repository.AttestationTravailRepository;
import com.example.nexus.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AttestationTravailService {

    @Autowired
    private AttestationTravailRepository attestationTravailRepository;

    @Autowired
    private UserRepository userRepository;
/* 
    @Transactional
    public AttestationTravail creerAttestation(Long userId, AttestationTravail attestation) {
        User utilisateur = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Valider la date souhaitée
        List<LocalDate> datesValides = getDatesValides(attestation.getType());
        if (!datesValides.contains(attestation.getDateSouhaitee())) {
            throw new RuntimeException("La date souhaitée n'est pas valide pour le type d'attestation sélectionné.");
        }

        attestation.setUtilisateur(utilisateur);
        attestation.setDateCreation(LocalDate.now());
        attestation.setEtatDemande(EtatDemande.EN_ATTENTE); // État par défaut
        attestation.setReference(null); // Référence reste null pour EN_ATTENTE

        return attestationTravailRepository.save(attestation);
    }
*/
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
/* 
        if (type == TypeAttestation.LEGALISE) {
            List<LocalDate> biWeeklyFridays = new ArrayList<>();
            for (int i = 0; i < fridays.size(); i++) {
                if (i % 2 == 0) {
                    biWeeklyFridays.add(fridays.get(i));
                }
            }
            return biWeeklyFridays;
        } else if (type == TypeAttestation.NON_LEGALISE) {
            return fridays;
        }
*/
        return fridays;
    }

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

    public List<AttestationTravail> listerParUtilisateur(Long userId) {
        return attestationTravailRepository.findByUtilisateurIdUser(userId);
    }

    public List<AttestationTravail> listerToutesLesAttestations() {
        return attestationTravailRepository.findAll();
    }
}
