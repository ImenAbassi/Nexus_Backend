package com.example.nexus.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.nexus.Entitie.AutorisationSortie;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.ValidationHistorique;
import com.example.nexus.Services.AutorisationSortieService;

@RestController
@RequestMapping("/autorisationSortie")
public class AutorisationSortieController {

    @Autowired
    private AutorisationSortieService autorisationSortieService;

    // Créer une nouvelle autorisation de sortie
    @PostMapping("/creer")
    public ResponseEntity<AutorisationSortie> creerDemande(@RequestBody AutorisationSortie autorisationSortie) {
        AutorisationSortie nouvelleAutorisation = autorisationSortieService.creerDemande(autorisationSortie);
        return ResponseEntity.ok(nouvelleAutorisation);
    }

    @PostMapping("/validerSuperviseurs/{id}")
    public ResponseEntity<Map<String, String>> validerParSuperviseurs(
            @PathVariable Long id) { // Utilisez le DTO
        try {
            autorisationSortieService.validerParSuperviseur(id, EtatDemande.APPROUVEE);
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

    // Valider par le chef de projet
    @PostMapping("/validerChefProjets/{id}")
    public ResponseEntity<Map<String, String>> validerParChefProjets(@PathVariable Long id) {
        try {
            autorisationSortieService.validerParChefProjet(id, EtatDemande.APPROUVEE);
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

    // Valider par les RH
    @PostMapping("/validerRHs/{id}")
    public ResponseEntity<Map<String, String>> validerParRHs(@PathVariable Long id) {
        try {
            autorisationSortieService.validerParRH(id, EtatDemande.APPROUVEE);
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

    // Récupérer l'historique des validations
    @GetMapping("/historique/{id}")
    public ResponseEntity<List<ValidationHistorique>> obtenirHistorique(@PathVariable Long id) {
        List<ValidationHistorique> historique = autorisationSortieService.obtenirHistorique(id);
        return ResponseEntity.ok(historique);
    }

    // Récupérer toutes les autorisations de sortie
    @GetMapping("/toutes")
    public ResponseEntity<List<AutorisationSortie>> getAllAutorisations() {
        List<AutorisationSortie> autorisations = autorisationSortieService.getAllAutorisations();
        return ResponseEntity.ok(autorisations);
    }

    // Récupérer une autorisation de sortie par son ID
    @GetMapping("/{id}")
    public ResponseEntity<AutorisationSortie> getAutorisationById(@PathVariable Long id) {
        AutorisationSortie autorisation = autorisationSortieService.getAutorisationById(id);
        return ResponseEntity.ok(autorisation);
    }

    // Supprimer une autorisation de sortie
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> deleteAutorisation(@PathVariable Long id) {
        try {
            autorisationSortieService.deleteAutorisation(id);
            return ResponseEntity.ok("Autorisation supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Mettre à jour une autorisation de sortie
    @PutMapping("/modifier/{id}")
    public ResponseEntity<AutorisationSortie> updateAutorisation(
            @PathVariable Long id,
            @RequestBody AutorisationSortie autorisationSortie) {
        AutorisationSortie updatedAutorisation = autorisationSortieService.updateAutorisation(id, autorisationSortie);
        return ResponseEntity.ok(updatedAutorisation);
    }

    // Récupérer les autorisations en attente de validation par le superviseur
    @GetMapping("/enAttenteSuperviseur")
    public ResponseEntity<List<AutorisationSortie>> getAutorisationsEnAttenteSuperviseur() {
        List<AutorisationSortie> autorisations = autorisationSortieService.getAutorisationsEnAttenteSuperviseur();
        return ResponseEntity.ok(autorisations);
    }

    // Récupérer les autorisations en attente de validation par le chef de projet
    @GetMapping("/enAttenteChefProjet")
    public ResponseEntity<List<AutorisationSortie>> getAutorisationsEnAttenteChefProjet() {
        List<AutorisationSortie> autorisations = autorisationSortieService.getAutorisationsEnAttenteChefProjet();
        return ResponseEntity.ok(autorisations);
    }

    // Récupérer les autorisations en attente de validation par les RH
    @GetMapping("/enAttenteRH")
    public ResponseEntity<List<AutorisationSortie>> getAutorisationsEnAttenteRH() {
        List<AutorisationSortie> autorisations = autorisationSortieService.getAutorisationsEnAttenteRH();
        return ResponseEntity.ok(autorisations);
    }

    // Récupérer les autorisations validées
    @GetMapping("/validees")
    public ResponseEntity<List<AutorisationSortie>> getAutorisationsValidees() {
        List<AutorisationSortie> autorisations = autorisationSortieService.getAutorisationsValidees();
        return ResponseEntity.ok(autorisations);
    }

    // Récupérer les autorisations refusées
    @GetMapping("/refusees")
    public ResponseEntity<List<AutorisationSortie>> getAutorisationsRefusees() {
        List<AutorisationSortie> autorisations = autorisationSortieService.getAutorisationsRefusees();
        return ResponseEntity.ok(autorisations);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AutorisationSortie>> getAutorisationsByUser(@PathVariable Long userId) {
        User utilisateur = new User();
        utilisateur.setIdUser(userId);

        List<AutorisationSortie> autorisations = autorisationSortieService.getAutorisationsByUser(utilisateur);
        return ResponseEntity.ok(autorisations);
    }

    // Récupérer toutes les autorisations de sortie pour un superviseur spécifique
    @GetMapping("/superviseur/{supervisorId}")
    public ResponseEntity<List<AutorisationSortie>> getAutorisationsForSupervisor(@PathVariable Long supervisorId) {
        User supervisor = new User();
        supervisor.setIdUser(supervisorId);

        List<AutorisationSortie> autorisations = autorisationSortieService.getAutorisationsForSupervisor(supervisor);
        return ResponseEntity.ok(autorisations);
    }

    // Récupérer toutes les autorisations de sortie pour un chef de projet spécifique
    @GetMapping("/chef-projet/{projectLeaderId}")
    public ResponseEntity<List<AutorisationSortie>> getAutorisationsForProjectLeader(@PathVariable Long projectLeaderId) {
        User projectLeader = new User();
        projectLeader.setIdUser(projectLeaderId);

        List<AutorisationSortie> autorisations = autorisationSortieService.getAutorisationsForProjectLeader(projectLeader);
        return ResponseEntity.ok(autorisations);
    }

        @PostMapping("/validerSuperviseur/{id}")
    public ResponseEntity<Map<String, String>> validerParSuperviseur(
            @PathVariable Long id,
            @RequestParam EtatDemande etat) { // Utilisez @RequestParam pour récupérer l'état
        try {
            autorisationSortieService.validerParSuperviseur(id, etat); // Passez l'état dynamiquement
            Map<String, String> response = new HashMap<>();
            response.put("message", "Demande validée par le superviseur avec l'état : " + etat);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "État invalide : " + etat);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/validerChefProjet/{id}")
    public ResponseEntity<Map<String, String>> validerParChefProjet(
            @PathVariable Long id,
            @RequestParam EtatDemande etat) { // Utilisez @RequestParam pour récupérer l'état
        try {
            autorisationSortieService.validerParChefProjet(id, etat); // Passez l'état dynamiquement
            Map<String, String> response = new HashMap<>();
            response.put("message", "Demande validée par le superviseur avec l'état : " + etat);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "État invalide : " + etat);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/validerRH/{id}")
    public ResponseEntity<Map<String, String>> validerParRH(
            @PathVariable Long id,
            @RequestParam EtatDemande etat) { // Utilisez @RequestParam pour récupérer l'état
        try {
            autorisationSortieService.validerParRH(id, etat); // Passez l'état dynamiquement
            Map<String, String> response = new HashMap<>();
            response.put("message", "Demande validée par le superviseur avec l'état : " + etat);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "État invalide : " + etat);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}