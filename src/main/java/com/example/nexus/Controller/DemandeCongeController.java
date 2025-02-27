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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.DemandeConge;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.User;
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
            @PathVariable Long id,
            @RequestParam EtatDemande etat) { // Utilisez @RequestParam pour récupérer l'état
        try {
            demandeCongeService.validerParSuperviseur(id, etat); // Passez l'état dynamiquement
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
            demandeCongeService.validerParChefProjet(id, etat); // Passez l'état dynamiquement
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
            demandeCongeService.validerParRH(id, etat); // Passez l'état dynamiquement
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

    @GetMapping("/superviseur/{supervisorId}")
    public ResponseEntity<List<DemandeConge>> getDemandesForSupervisor(@PathVariable Long supervisorId) {
        // Fetch the supervisor user (you can use a UserRepository to find the user by ID)
        User supervisor = new User();
        supervisor.setIdUser(supervisorId);

        // Get all leave requests for users supervised by this supervisor
        List<DemandeConge> demandes = demandeCongeService.getDemandesForSupervisor(supervisor);

        return ResponseEntity.ok(demandes);
    }

    // Get all leave requests for users where the given user is their project leader
    @GetMapping("/chef-projet/{projectLeaderId}")
    public ResponseEntity<List<DemandeConge>> getDemandesForProjectLeader(@PathVariable Long projectLeaderId) {
        // Fetch the project leader user (you can use a UserRepository to find the user by ID)
        User projectLeader = new User();
        projectLeader.setIdUser(projectLeaderId);

        // Get all leave requests for users led by this project leader
        List<DemandeConge> demandes = demandeCongeService.getDemandesForProjectLeader(projectLeader);

        return ResponseEntity.ok(demandes);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DemandeConge>> getDemandesByUser(@PathVariable Long userId) {
        // Créer un objet User avec l'ID fourni
        User user = new User();
        user.setIdUser(userId);

        // Récupérer les demandes de congé pour cet utilisateur
        List<DemandeConge> demandes = demandeCongeService.getDemandesByUser(user);

        return ResponseEntity.ok(demandes);
    }
}
