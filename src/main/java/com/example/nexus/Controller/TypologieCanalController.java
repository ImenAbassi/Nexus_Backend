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

import com.example.nexus.Entitie.TypologieCanal;
import com.example.nexus.Services.TypologieCanalService;

@RestController
@RequestMapping("/typologie-canaux")
public class TypologieCanalController {

    @Autowired
    private TypologieCanalService typologieCanalService;

    // Récupérer toutes les typologies de canal
    @GetMapping
    public List<TypologieCanal> getAllTypologieCanaux() {
        return typologieCanalService.getAllTypologieCanaux();
    }

    // Récupérer une typologie de canal par son ID
    @GetMapping("/{id}")
    public ResponseEntity<TypologieCanal> getTypologieCanalById(@PathVariable Long id) {
        Optional<TypologieCanal> typologieCanal = typologieCanalService.getTypologieCanalById(id);
        return typologieCanal.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Créer une nouvelle typologie de canal
    @PostMapping
    public ResponseEntity<TypologieCanal> createTypologieCanal(@RequestBody TypologieCanal typologieCanal) {
        TypologieCanal newTypologieCanal = typologieCanalService.createTypologieCanal(typologieCanal);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTypologieCanal);
    }

    // Mettre à jour une typologie de canal existante
    @PutMapping("/{id}")
    public ResponseEntity<TypologieCanal> updateTypologieCanal(@PathVariable Long id, @RequestBody TypologieCanal typologieCanalDetails) {
        TypologieCanal updatedTypologieCanal = typologieCanalService.updateTypologieCanal(id, typologieCanalDetails);
        return updatedTypologieCanal != null ? ResponseEntity.ok(updatedTypologieCanal) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Supprimer une typologie de canal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypologieCanal(@PathVariable Long id) {
        typologieCanalService.deleteTypologieCanal(id);
        return ResponseEntity.noContent().build();
    }
}
