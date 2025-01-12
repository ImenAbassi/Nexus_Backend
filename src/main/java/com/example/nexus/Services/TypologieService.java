package com.example.nexus.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Typologie;
import com.example.nexus.Repository.TypologieRepository;

@Service
public class TypologieService {

    @Autowired
    private TypologieRepository typologieRepository;

    // Récupérer toutes les typologies
    public List<Typologie> getAllTypologies() {
        return typologieRepository.findAll();
    }

    // Récupérer une typologie par son ID
    public Optional<Typologie> getTypologieById(Long id) {
        return typologieRepository.findById(id);
    }

    // Créer une nouvelle typologie
    public Typologie createTypologie(Typologie typologie) {
        return typologieRepository.save(typologie);
    }

    // Mettre à jour une typologie existante
    public Typologie updateTypologie(Long id, Typologie typologieDetails) {
        Optional<Typologie> typologie = typologieRepository.findById(id);
        if (typologie.isPresent()) {
            Typologie existingTypologie = typologie.get();
            existingTypologie.setNom(typologieDetails.getNom());
            existingTypologie.setDescription(typologieDetails.getDescription());
            return typologieRepository.save(existingTypologie);
        }
        return null; // Retourner null si la typologie n'existe pas
    }

    // Supprimer une typologie
    public void deleteTypologie(Long id) {
        typologieRepository.deleteById(id);
    }
}
