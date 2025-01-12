package com.example.nexus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.AutorisationSortie;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.ValidationHistorique;
import com.example.nexus.Services.AutorisationSortieService;

@RestController
@RequestMapping("/autorisationSortie")
public class AutorisationSortieController {

    @Autowired
    private AutorisationSortieService autorisationSortieService;

    @PostMapping("/creer")
    public ResponseEntity<AutorisationSortie> creerDemande(@RequestBody AutorisationSortie autorisationSortie) {
        AutorisationSortie nouvelleAutorisation = autorisationSortieService.creerDemande(autorisationSortie);
        return ResponseEntity.ok(nouvelleAutorisation);
    }

    @PostMapping("/validerSuperviseur/{id}")
    public ResponseEntity<String> validerParSuperviseur(@PathVariable Long id, @RequestBody EtatDemande etat) {
        try {
            autorisationSortieService.validerParSuperviseur(id, etat);
            return ResponseEntity.ok("Demande validée par le superviseur");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validerChefProjet/{id}")
    public ResponseEntity<String> validerParChefProjet(@PathVariable Long id, @RequestBody EtatDemande etat) {
        try {
            autorisationSortieService.validerParChefProjet(id, etat);
            return ResponseEntity.ok("Demande validée par le chef de projet");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/historique/{id}")
    public ResponseEntity<List<ValidationHistorique>> obtenirHistorique(@PathVariable Long id) {
        List<ValidationHistorique> historique = autorisationSortieService.obtenirHistorique(id);
        return ResponseEntity.ok(historique);
    }
}
