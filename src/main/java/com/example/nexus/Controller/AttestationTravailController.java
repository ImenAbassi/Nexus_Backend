package com.example.nexus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.AttestationTravail;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Services.AttestationTravailService;

@RestController
@RequestMapping("/attestation-travail")
public class AttestationTravailController {

    @Autowired
    private AttestationTravailService attestationTravailService;
/* 
    // Créer une nouvelle attestation de travail pour un utilisateur
    @PostMapping("/creer/{userId}")
    public AttestationTravail creerAttestation(@PathVariable Long userId,
            @RequestBody AttestationTravail attestation) {
        return attestationTravailService.creerAttestation(userId, attestation);
    }
*/
    @GetMapping("/utilisateur/{userId}")
    public List<AttestationTravail> obtenirAttestationsParUtilisateur(@PathVariable Long userId) {
        return attestationTravailService.listerParUtilisateur(userId);
    }

    @PutMapping("/{attestationId}/etat")
    public ResponseEntity<AttestationTravail> mettreAJourEtat(@PathVariable Long attestationId,
            @RequestParam EtatDemande nouvelEtat) {
        AttestationTravail updatedAttestation = attestationTravailService.mettreAJourEtat(attestationId, nouvelEtat);
        return ResponseEntity.ok(updatedAttestation);
    }

    @GetMapping("/toutes")
    public List<AttestationTravail> obtenirToutesLesAttestations() {
        return attestationTravailService.listerToutesLesAttestations(); // Appelle le service
    }

}
