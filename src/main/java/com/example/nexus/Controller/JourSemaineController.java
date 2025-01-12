package com.example.nexus.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.JourSemaine;
import com.example.nexus.Services.JourSemaineService;

@RestController
@RequestMapping("/jour-semaine")
public class JourSemaineController {

    @Autowired
    private JourSemaineService jourSemaineService;

    // Endpoint pour récupérer tous les jours de la semaine
    @GetMapping
    public List<JourSemaine> getAllJourSemaine() {
        return jourSemaineService.getAllJourSemaine();
    }

    // Endpoint pour récupérer un jour de la semaine par son ID
    @GetMapping("/{id}")
    public ResponseEntity<JourSemaine> getJourSemaineById(@PathVariable Long id) {
        Optional<JourSemaine> jourSemaine = jourSemaineService.getJourSemaineById(id);
        return jourSemaine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour ajouter un nouveau jour de la semaine
    @PostMapping
    public ResponseEntity<JourSemaine> addJourSemaine(@RequestBody JourSemaine jourSemaine) {
        JourSemaine newJourSemaine = jourSemaineService.addJourSemaine(jourSemaine);
        return new ResponseEntity<>(newJourSemaine, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour un jour de la semaine
    @PutMapping("/{id}")
    public ResponseEntity<JourSemaine> updateJourSemaine(@PathVariable Long id, @RequestBody JourSemaine jourSemaineDetails) {
        JourSemaine updatedJourSemaine = jourSemaineService.updateJourSemaine(id, jourSemaineDetails);
        return updatedJourSemaine != null ? ResponseEntity.ok(updatedJourSemaine) : ResponseEntity.notFound().build();
    }

    // Endpoint pour supprimer un jour de la semaine par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJourSemaine(@PathVariable Long id) {
        jourSemaineService.deleteJourSemaine(id);
        return ResponseEntity.noContent().build();
    }
}
