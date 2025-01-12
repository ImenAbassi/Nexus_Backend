package com.example.nexus.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.DemandeConge;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Services.DemandeCongeService;

@RestController
@RequestMapping("/conges")
public class DemandeCongeController {

    @Autowired
    private DemandeCongeService demandeCongeService;

    @GetMapping
    public List<DemandeConge> getAllConges() {
        return demandeCongeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeConge> getCongeById(@PathVariable Long id) {
        Optional<DemandeConge> conge = demandeCongeService.findById(id);
        return conge.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public ResponseEntity<DemandeConge> creerDemande(@RequestBody DemandeConge demandeConge) {
        DemandeConge nouvelleDemande = demandeCongeService.creerDemande(demandeConge);
        return ResponseEntity.ok(nouvelleDemande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConge(@PathVariable Long id) {
        demandeCongeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validerSuperviseur/{id}")
    public ResponseEntity<String> validerParSuperviseur(@PathVariable Long id, @RequestBody EtatDemande etat) {
        try {
            demandeCongeService.validerParSuperviseur(id, etat);
            return ResponseEntity.ok("Demande validée par le superviseur");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validerChefProjet/{id}")
    public ResponseEntity<String> validerParChefProjet(@PathVariable Long id, @RequestBody EtatDemande etat) {
        try {
            demandeCongeService.validerParChefProjet(id, etat);
            return ResponseEntity.ok("Demande validée par le chef de projet");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
