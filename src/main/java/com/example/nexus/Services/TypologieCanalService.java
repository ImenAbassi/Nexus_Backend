package com.example.nexus.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.TypologieCanal;
import com.example.nexus.Repository.TypologieCanalRepository;

@Service
public class TypologieCanalService {

    @Autowired
    private TypologieCanalRepository typologieCanalRepository;

    // Récupérer toutes les typologies de canal
    public List<TypologieCanal> getAllTypologieCanaux() {
        return typologieCanalRepository.findAll();
    }

    // Récupérer une typologie de canal par son ID
    public Optional<TypologieCanal> getTypologieCanalById(Long id) {
        return typologieCanalRepository.findById(id);
    }

    // Créer une nouvelle typologie de canal
    public TypologieCanal createTypologieCanal(TypologieCanal typologieCanal) {
        return typologieCanalRepository.save(typologieCanal);
    }

    // Mettre à jour une typologie de canal existante
    public TypologieCanal updateTypologieCanal(Long id, TypologieCanal typologieCanalDetails) {
        Optional<TypologieCanal> typologieCanal = typologieCanalRepository.findById(id);
        if (typologieCanal.isPresent()) {
            TypologieCanal existingTypologieCanal = typologieCanal.get();
            existingTypologieCanal.setNom(typologieCanalDetails.getNom());
            existingTypologieCanal.setDescription(typologieCanalDetails.getDescription());
            return typologieCanalRepository.save(existingTypologieCanal);
        }
        return null; // Retourner null si la typologie de canal n'existe pas
    }

    // Supprimer une typologie de canal
    public void deleteTypologieCanal(Long id) {
        typologieCanalRepository.deleteById(id);
    }
}
