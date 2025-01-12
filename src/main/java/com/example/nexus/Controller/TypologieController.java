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

import com.example.nexus.Entitie.Typologie;
import com.example.nexus.Services.TypologieService;

@RestController
@RequestMapping("/typologies")
public class TypologieController {

    @Autowired
    private TypologieService typologieService;

    // Récupérer toutes les typologies
    @GetMapping
    public List<Typologie> getAllTypologies() {
        return typologieService.getAllTypologies();
    }

    // Récupérer une typologie par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Typologie> getTypologieById(@PathVariable Long id) {
        Optional<Typologie> typologie = typologieService.getTypologieById(id);
        return typologie.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Créer une nouvelle typologie
    @PostMapping
    public ResponseEntity<Typologie> createTypologie(@RequestBody Typologie typologie) {
        Typologie newTypologie = typologieService.createTypologie(typologie);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTypologie);
    }

    // Mettre à jour une typologie existante
    @PutMapping("/{id}")
    public ResponseEntity<Typologie> updateTypologie(@PathVariable Long id, @RequestBody Typologie typologieDetails) {
        Typologie updatedTypologie = typologieService.updateTypologie(id, typologieDetails);
        return updatedTypologie != null ? ResponseEntity.ok(updatedTypologie) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Supprimer une typologie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypologie(@PathVariable Long id) {
        typologieService.deleteTypologie(id);
        return ResponseEntity.noContent().build();
    }
}
