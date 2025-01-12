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

import com.example.nexus.Entitie.Langue;
import com.example.nexus.Services.LangueService;

@RestController
@RequestMapping("/langues")
public class LangueController {

    @Autowired
    private LangueService langueService;

    // Récupérer toutes les langues
    @GetMapping
    public List<Langue> getAllLangues() {
        return langueService.getAllLangues();
    }

    // Récupérer une langue par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Langue> getLangueById(@PathVariable Long id) {
        Optional<Langue> langue = langueService.getLangueById(id);
        return langue.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Créer une nouvelle langue
    @PostMapping
    public ResponseEntity<Langue> createLangue(@RequestBody Langue langue) {
        Langue newLangue = langueService.createLangue(langue);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLangue);
    }

    // Mettre à jour une langue existante
    @PutMapping("/{id}")
    public ResponseEntity<Langue> updateLangue(@PathVariable Long id, @RequestBody Langue langueDetails) {
        Langue updatedLangue = langueService.updateLangue(id, langueDetails);
        return updatedLangue != null ? ResponseEntity.ok(updatedLangue) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Supprimer une langue
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLangue(@PathVariable Long id) {
        langueService.deleteLangue(id);
        return ResponseEntity.noContent().build();
    }
}
