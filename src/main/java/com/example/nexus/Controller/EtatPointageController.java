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

import com.example.nexus.Entitie.EtatPointage;
import com.example.nexus.Services.EtatPointageService;

@RestController
@RequestMapping("/etat-pointage")
public class EtatPointageController {

    @Autowired
    private EtatPointageService etatPointageService;

    @GetMapping
    public ResponseEntity<List<EtatPointage>> getAllEtatPointages() {
        List<EtatPointage> etatPointages = etatPointageService.getAllEtatPointages();
        return new ResponseEntity<>(etatPointages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtatPointage> getEtatPointageById(@PathVariable("id") Long id) {
        Optional<EtatPointage> etatPointage = etatPointageService.getEtatPointageById(id);
        return etatPointage.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EtatPointage> createEtatPointage(@RequestBody EtatPointage etatPointage) {
        EtatPointage createdEtatPointage = etatPointageService.createEtatPointage(etatPointage);
        return new ResponseEntity<>(createdEtatPointage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtatPointage> updateEtatPointage(@PathVariable("id") Long id,
                                                           @RequestBody EtatPointage etatPointage) {
        EtatPointage updatedEtatPointage = etatPointageService.updateEtatPointage(id, etatPointage);
        return updatedEtatPointage != null ? new ResponseEntity<>(updatedEtatPointage, HttpStatus.OK)
                                          : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtatPointage(@PathVariable("id") Long id) {
        etatPointageService.deleteEtatPointage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
