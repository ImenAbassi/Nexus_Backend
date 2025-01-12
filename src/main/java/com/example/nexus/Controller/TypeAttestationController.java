package com.example.nexus.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.TypeAttestation;
import com.example.nexus.Services.TypeAttestationService;

@RestController
@RequestMapping("/type-attestations")
public class TypeAttestationController {

    @Autowired
    private TypeAttestationService typeAttestationService;

    @PostMapping
    public TypeAttestation createTypeAttestation(@RequestBody TypeAttestation typeAttestation) {
        return typeAttestationService.createTypeAttestation(typeAttestation);
    }

    @GetMapping
    public List<TypeAttestation> getAllTypeAttestations() {
        return typeAttestationService.getAllTypeAttestations();
    }

    @GetMapping("/{id}")
    public TypeAttestation getTypeAttestationById(@PathVariable Long id) {
        return typeAttestationService.getTypeAttestationById(id);
    }

    @PutMapping("/{id}")
    public TypeAttestation updateTypeAttestation(@PathVariable Long id, @RequestBody TypeAttestation typeAttestation) {
        return typeAttestationService.updateTypeAttestation(id, typeAttestation);
    }

    @DeleteMapping("/{id}")
    public void deleteTypeAttestation(@PathVariable Long id) {
        typeAttestationService.deleteTypeAttestation(id);
    }
}
