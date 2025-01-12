package com.example.nexus.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.TypeAttestation;
import com.example.nexus.Repository.TypeAttestationRepository;

@Service
public class TypeAttestationService {

    @Autowired
    private TypeAttestationRepository typeAttestationRepository;

    public TypeAttestation createTypeAttestation(TypeAttestation typeAttestation) {
        return typeAttestationRepository.save(typeAttestation);
    }

    public List<TypeAttestation> getAllTypeAttestations() {
        return typeAttestationRepository.findAll();
    }

    public TypeAttestation getTypeAttestationById(Long id) {
        return typeAttestationRepository.findById(id).orElse(null);
    }

    public TypeAttestation updateTypeAttestation(Long id, TypeAttestation typeAttestationDetails) {
        TypeAttestation typeAttestation = typeAttestationRepository.findById(id).orElse(null);
        if (typeAttestation != null) {
            typeAttestation.setNom(typeAttestationDetails.getNom());
            return typeAttestationRepository.save(typeAttestation);
        }
        return null;
    }

    public void deleteTypeAttestation(Long id) {
        typeAttestationRepository.deleteById(id);
    }
}
