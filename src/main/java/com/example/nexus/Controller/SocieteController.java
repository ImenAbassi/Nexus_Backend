package com.example.nexus.Controller;



import com.example.nexus.Entitie.Societe;
import com.example.nexus.Services.SocieteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/societes")
public class SocieteController {

    @Autowired
    private SocieteService societeService;

    @PostMapping
    public Societe createSociete(@RequestBody Societe societe) {
        return societeService.createSociete(societe);
    }

    @GetMapping
    public List<Societe> getAllSocietes() {
        return societeService.getAllSocietes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Societe> getSocieteById(@PathVariable Long id) {
        return societeService.getSocieteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Societe> updateSociete(@PathVariable Long id, @RequestBody Societe societeDetails) {
        Societe updatedSociete = societeService.updateSociete(id, societeDetails);
        return ResponseEntity.ok(updatedSociete);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSociete(@PathVariable Long id) {
        societeService.deleteSociete(id);
        return ResponseEntity.noContent().build();
    }
}
