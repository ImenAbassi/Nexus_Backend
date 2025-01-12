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

import com.example.nexus.Entitie.EtatUser;
import com.example.nexus.Services.EtatUserService;

@RestController
@RequestMapping("/etatusers")
public class EtatUserController {

    @Autowired
    private EtatUserService etatUserService;

    // Récupérer tous les états utilisateurs
    @GetMapping
    public List<EtatUser> getAllEtatUser() {
        return etatUserService.getAllEtatUser();
    }

    // Récupérer un état utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<EtatUser> getEtatUserById(@PathVariable Long id) {
        Optional<EtatUser> etatUser = etatUserService.getEtatUserById(id);
        return etatUser.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Créer un nouvel état utilisateur
    @PostMapping
    public ResponseEntity<EtatUser> createEtatUser(@RequestBody EtatUser etatUser) {
        EtatUser newEtatUser = etatUserService.createEtatUser(etatUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEtatUser);
    }

    // Mettre à jour un état utilisateur existant
    @PutMapping("/{id}")
    public ResponseEntity<EtatUser> updateEtatUser(@PathVariable Long id, @RequestBody EtatUser etatUserDetails) {
        EtatUser updatedEtatUser = etatUserService.updateEtatUser(id, etatUserDetails);
        return updatedEtatUser != null ? ResponseEntity.ok(updatedEtatUser) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Supprimer un état utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtatUser(@PathVariable Long id) {
        etatUserService.deleteEtatUser(id);
        return ResponseEntity.noContent().build();
    }
}
