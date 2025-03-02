package com.example.nexus.Services;

import com.example.nexus.Entitie.TypePointage;
import com.example.nexus.Repository.TypePointageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePointageService {

    @Autowired
    private TypePointageRepository typePointageRepository;

    // Create a new privilege
    public TypePointage createTypePointage(TypePointage privilege) {
        return typePointageRepository.save(privilege);
    }

    // Get all privileges
    public List<TypePointage> getAllTypePointages() {
        return typePointageRepository.findAll();
    }

    // Get a privilege by ID
    public Optional<TypePointage> getTypePointageById(Long id) {
        return typePointageRepository.findById(id);
    }

    // Update an existing privilege
    public TypePointage updateTypePointage(Long id, TypePointage updatedEntity) {
        return typePointageRepository.findById(id)
                .map(privilege -> {
                    privilege.setName(updatedEntity.getName());
                    return typePointageRepository.save(privilege);
                })
                .orElseThrow(() -> new RuntimeException("TypePointage not found with ID: " + id));
    }

    // Delete a privilege by ID
    public void deleteTypePointage(Long id) {
        typePointageRepository.deleteById(id);
    }
}