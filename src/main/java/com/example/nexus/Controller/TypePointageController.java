package com.example.nexus.Controller;


import com.example.nexus.Entitie.TypePointage;
import com.example.nexus.Services.TypePointageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/typepointage")
public class TypePointageController  {

    @Autowired
    private TypePointageService typePointageService;

     @PostMapping
    public ResponseEntity<TypePointage> createTypePointage(@RequestBody TypePointage privilege) {
        TypePointage createdTypePointage = typePointageService.createTypePointage(privilege);
        return new ResponseEntity<>(createdTypePointage, HttpStatus.CREATED);
    }
 @GetMapping
    public ResponseEntity<List<TypePointage>> getAllTypePointages() {
        List<TypePointage> privileges = typePointageService.getAllTypePointages();
        return new ResponseEntity<>(privileges, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TypePointage> getTypePointageById(@PathVariable Long id) {
        return typePointageService.getTypePointageById(id)
                .map(privilege -> new ResponseEntity<>(privilege, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
@PutMapping("/{id}")
    public ResponseEntity<TypePointage> updateTypePointage(@PathVariable Long id,@RequestBody TypePointage updatedTypePointage) {
        try {
            TypePointage updated = typePointageService.updateTypePointage(id, updatedTypePointage);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypePointage(@PathVariable Long id) {
        typePointageService.deleteTypePointage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}