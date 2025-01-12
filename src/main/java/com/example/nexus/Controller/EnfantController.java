package com.example.nexus.Controller;



import com.example.nexus.Entitie.Enfant;
import com.example.nexus.Services.EnfantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enfants")
public class EnfantController {

    @Autowired
    private EnfantService enfantService;

    @PostMapping
    public Enfant createEnfant(@RequestBody Enfant enfant) {
        return enfantService.createEnfant(enfant);
    }

    @GetMapping
    public List<Enfant> getAllEnfants() {
        return enfantService.getAllEnfants();
    }

    @GetMapping("/{idEnfant}")
    public ResponseEntity<Enfant> getEnfantById(@PathVariable Integer idEnfant) {
        return enfantService.getEnfantById(idEnfant)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idEnfant}")
    public ResponseEntity<Enfant> updateEnfant(@PathVariable Integer idEnfant, @RequestBody Enfant enfantDetails) {
        Enfant updatedEnfant = enfantService.updateEnfant(idEnfant, enfantDetails);
        return ResponseEntity.ok(updatedEnfant);
    }

    @DeleteMapping("/{idEnfant}")
    public ResponseEntity<Void> deleteEnfant(@PathVariable Integer idEnfant) {
        enfantService.deleteEnfant(idEnfant);
        return ResponseEntity.noContent().build();
    }
}
