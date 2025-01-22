package com.example.nexus.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Map<String, String>> validerParSuperviseur(
            @PathVariable Long id) { // Utilisez le DTO
        try {
            demandeCongeService.validerParSuperviseur(id, EtatDemande.APPROUVEE);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Demande validée par le superviseur : " + EtatDemande.APPROUVEE);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "État invalide : " + EtatDemande.APPROUVEE);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

     @PostMapping("/validerChefProjet/{id}")
    public ResponseEntity<Map<String, String>> validerParChefProjet(@PathVariable Long id) {
        try {
            demandeCongeService.validerParChefProjet(id, EtatDemande.APPROUVEE);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Demande validée par le superviseur : " + EtatDemande.APPROUVEE);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "État invalide : " + EtatDemande.APPROUVEE);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
