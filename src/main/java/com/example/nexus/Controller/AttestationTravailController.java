package com.example.nexus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    // Créer une nouvelle attestation de travail pour un utilisateur
    @PostMapping("/creer")
    public ResponseEntity<AttestationTravail> creerAttestation(
            @RequestBody AttestationTravail attestation) {
        AttestationTravail nouvelleAttestation = attestationTravailService.creerAttestation(attestation);
        return ResponseEntity.ok(nouvelleAttestation);
    }

    // Récupérer toutes les attestations de travail
    @GetMapping("/toutes")
    public ResponseEntity<List<AttestationTravail>> obtenirToutesLesAttestations() {
        List<AttestationTravail> attestations = attestationTravailService.listerToutesLesAttestations();
        return ResponseEntity.ok(attestations);
    }

    // Récupérer une attestation de travail par son ID
    @GetMapping("/{id}")
    public ResponseEntity<AttestationTravail> obtenirAttestationParId(@PathVariable Long id) {
        AttestationTravail attestation = attestationTravailService.obtenirAttestationParId(id);
        return ResponseEntity.ok(attestation);
    }

    // Récupérer les attestations de travail d'un utilisateur
    @GetMapping("/utilisateur/{userId}")
    public ResponseEntity<List<AttestationTravail>> obtenirAttestationsParUtilisateur(@PathVariable Long userId) {
        List<AttestationTravail> attestations = attestationTravailService.listerParUtilisateur(userId);
        return ResponseEntity.ok(attestations);
    }

    // Mettre à jour l'état d'une attestation de travail
    @PutMapping("/{attestationId}/etat")
    public ResponseEntity<AttestationTravail> mettreAJourEtat(
            @PathVariable Long attestationId) {
        AttestationTravail updatedAttestation = attestationTravailService.mettreAJourEtat(attestationId, EtatDemande.APPROUVEE);
        return ResponseEntity.ok(updatedAttestation);
    }

    // Supprimer une attestation de travail
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerAttestation(@PathVariable Long id) {
        attestationTravailService.supprimerAttestation(id);
        return ResponseEntity.ok("Attestation supprimée avec succès");
    }
}