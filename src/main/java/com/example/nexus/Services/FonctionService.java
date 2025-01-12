package com.example.nexus.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Fonction;
import com.example.nexus.Repository.FonctionRepository;

@Service
public class FonctionService {

    @Autowired
    private FonctionRepository fonctionRepository;

    public Fonction createFonction(Fonction fonction) {
        return fonctionRepository.save(fonction);
    }

    public List<Fonction> getAllFonctions() {
        return fonctionRepository.findAll();
    }

    public Fonction getFonctionById(Long id) {
        return fonctionRepository.findById(id).orElse(null);
    }

    public Fonction updateFonction(Long id, Fonction fonctionDetails) {
        Fonction fonction = fonctionRepository.findById(id).orElse(null);
        if (fonction != null) {
            fonction.setNom(fonctionDetails.getNom());
            return fonctionRepository.save(fonction);
        }
        return null;
    }

    public void deleteFonction(Long id) {
        fonctionRepository.deleteById(id);
    }
}
