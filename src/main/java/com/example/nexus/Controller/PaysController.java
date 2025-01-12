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

import com.example.nexus.Entitie.Pays;
import com.example.nexus.Services.PaysService;

@RestController
@RequestMapping("/pays")
public class PaysController {

    @Autowired
    private PaysService paysService;

    // Récupérer tous les pays
    @GetMapping
    public List<Pays> getAllPays() {
        return paysService.getAllPays();
    }

    // Récupérer un pays par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Pays> getPaysById(@PathVariable Long id) {
        Optional<Pays> pays = paysService.getPaysById(id);
        return pays.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Créer un nouveau pays
    @PostMapping
    public ResponseEntity<Pays> createPays(@RequestBody Pays pays) {
        Pays newPays = paysService.createPays(pays);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPays);
    }

    // Mettre à jour un pays existant
    @PutMapping("/{id}")
    public ResponseEntity<Pays> updatePays(@PathVariable Long id, @RequestBody Pays paysDetails) {
        Pays updatedPays = paysService.updatePays(id, paysDetails);
        return updatedPays != null ? ResponseEntity.ok(updatedPays) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Supprimer un pays
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePays(@PathVariable Long id) {
        paysService.deletePays(id);
        return ResponseEntity.noContent().build();
    }
}
