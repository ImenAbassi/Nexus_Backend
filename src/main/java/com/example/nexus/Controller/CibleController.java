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

import com.example.nexus.Entitie.Cible;
import com.example.nexus.Services.CibleService;

@RestController
@RequestMapping("/cibles")
public class CibleController {

    @Autowired
    private CibleService cibleService;

    // Récupérer toutes les cibles
    @GetMapping
    public List<Cible> getAllCibles() {
        return cibleService.getAllCibles();
    }

    // Récupérer une cible par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Cible> getCibleById(@PathVariable Long id) {
        Optional<Cible> cible = cibleService.getCibleById(id);
        return cible.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Créer une nouvelle cible
    @PostMapping
    public ResponseEntity<Cible> createCible(@RequestBody Cible cible) {
        Cible newCible = cibleService.createCible(cible);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCible);
    }

    // Mettre à jour une cible existante
    @PutMapping("/{id}")
    public ResponseEntity<Cible> updateCible(@PathVariable Long id, @RequestBody Cible cibleDetails) {
        Cible updatedCible = cibleService.updateCible(id, cibleDetails);
        return updatedCible != null ? ResponseEntity.ok(updatedCible) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Supprimer une cible
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCible(@PathVariable Long id) {
        cibleService.deleteCible(id);
        return ResponseEntity.noContent().build();
    }
}
