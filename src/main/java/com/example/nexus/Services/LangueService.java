package com.example.nexus.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Langue;
import com.example.nexus.Repository.LangueRepository;

@Service
public class LangueService {

    @Autowired
    private LangueRepository langueRepository;

    // Récupérer toutes les langues
    public List<Langue> getAllLangues() {
        return langueRepository.findAll();
    }

    // Récupérer une langue par son ID
    public Optional<Langue> getLangueById(Long id) {
        return langueRepository.findById(id);
    }

    // Créer une nouvelle langue
    public Langue createLangue(Langue langue) {
        return langueRepository.save(langue);
    }

    // Mettre à jour une langue existante
    public Langue updateLangue(Long id, Langue langueDetails) {
        Optional<Langue> langue = langueRepository.findById(id);
        if (langue.isPresent()) {
            Langue existingLangue = langue.get();
            existingLangue.setNom(langueDetails.getNom());
            return langueRepository.save(existingLangue);
        }
        return null; // Retourner null si la langue n'existe pas
    }

    // Supprimer une langue
    public void deleteLangue(Long id) {
        langueRepository.deleteById(id);
    }
}
