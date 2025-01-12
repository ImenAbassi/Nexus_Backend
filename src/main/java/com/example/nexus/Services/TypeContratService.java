package com.example.nexus.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.TypeContrat;
import com.example.nexus.Repository.TypeContratRepository;

@Service
public class TypeContratService {

    @Autowired
    private TypeContratRepository typeContratRepository;

    public TypeContrat createTypeContrat(TypeContrat typeContrat) {
        return typeContratRepository.save(typeContrat);
    }

    public List<TypeContrat> getAllTypeContrats() {
        return typeContratRepository.findAll();
    }

    public TypeContrat getTypeContratById(Long id) {
        return typeContratRepository.findById(id).orElse(null);
    }

    public TypeContrat updateTypeContrat(Long id, TypeContrat typeContratDetails) {
        TypeContrat typeContrat = typeContratRepository.findById(id).orElse(null);
        if (typeContrat != null) {
            typeContrat.setNom(typeContratDetails.getNom());
            return typeContratRepository.save(typeContrat);
        }
        return null;
    }

    public void deleteTypeContrat(Long id) {
        typeContratRepository.deleteById(id);
    }
}
