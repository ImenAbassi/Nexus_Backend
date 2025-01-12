package com.example.nexus.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.TypeConge;
import com.example.nexus.Repository.TypeCongeRepository;

@Service
public class TypeCongeService {

    @Autowired
    private TypeCongeRepository typeCongeRepository;

    public TypeConge createTypeConge(TypeConge typeConge) {
        return typeCongeRepository.save(typeConge);
    }

    public List<TypeConge> getAllTypeConges() {
        return typeCongeRepository.findAll();
    }

    public TypeConge getTypeCongeById(Long id) {
        return typeCongeRepository.findById(id).orElse(null);
    }

    public TypeConge updateTypeConge(Long id, TypeConge typeCongeDetails) {
        TypeConge typeConge = typeCongeRepository.findById(id).orElse(null);
        if (typeConge != null) {
            typeConge.setNom(typeCongeDetails.getNom());
            return typeCongeRepository.save(typeConge);
        }
        return null;
    }

    public void deleteTypeConge(Long id) {
        typeCongeRepository.deleteById(id);
    }
}
