package com.example.nexus.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Cible;
import com.example.nexus.Repository.CibleRepository;

@Service
public class CibleService {

    @Autowired
    private CibleRepository cibleRepository;

    // Récupérer toutes les cibles
    public List<Cible> getAllCibles() {
        return cibleRepository.findAll();
    }

    // Récupérer une cible par son ID
    public Optional<Cible> getCibleById(Long id) {
        return cibleRepository.findById(id);
    }

    // Créer une nouvelle cible
    public Cible createCible(Cible cible) {
        return cibleRepository.save(cible);
    }

    // Mettre à jour une cible existante
    public Cible updateCible(Long id, Cible cibleDetails) {
        Optional<Cible> cible = cibleRepository.findById(id);
        if (cible.isPresent()) {
            Cible existingCible = cible.get();
            existingCible.setType(cibleDetails.getType());
            existingCible.setDescription(cibleDetails.getDescription());
            return cibleRepository.save(existingCible);
        }
        return null; // Retourne null si la cible n'existe pas
    }

    // Supprimer une cible
    public void deleteCible(Long id) {
        cibleRepository.deleteById(id);
    }
}
