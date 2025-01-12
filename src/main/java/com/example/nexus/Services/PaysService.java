package com.example.nexus.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Pays;
import com.example.nexus.Repository.PaysRepository;

@Service
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    // Récupérer tous les pays
    public List<Pays> getAllPays() {
        return paysRepository.findAll();
    }

    // Récupérer un pays par son ID
    public Optional<Pays> getPaysById(Long id) {
        return paysRepository.findById(id);
    }

    // Créer un nouveau pays
    public Pays createPays(Pays pays) {
        return paysRepository.save(pays);
    }

    // Mettre à jour un pays existant
    public Pays updatePays(Long id, Pays paysDetails) {
        Optional<Pays> pays = paysRepository.findById(id);
        if (pays.isPresent()) {
            Pays existingPays = pays.get();
            existingPays.setNom(paysDetails.getNom());
            return paysRepository.save(existingPays);
        }
        return null; // Retourner null si le pays n'existe pas
    }

    // Supprimer un pays
    public void deletePays(Long id) {
        paysRepository.deleteById(id);
    }
}
