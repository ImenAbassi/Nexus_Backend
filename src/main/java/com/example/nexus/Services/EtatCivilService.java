package com.example.nexus.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.EtatCivil;
import com.example.nexus.Repository.EtatCivilRepository;

@Service
public class EtatCivilService {

    @Autowired
    private EtatCivilRepository etatCivilRepository;

    // Créer un nouvel état civil
    public EtatCivil createEtatCivil(EtatCivil etatCivil) {
        return etatCivilRepository.save(etatCivil);
    }

    // Récupérer tous les états civils
    public List<EtatCivil> getAllEtatCivils() {
        return etatCivilRepository.findAll();
    }

    // Récupérer un état civil par son ID
    public EtatCivil getEtatCivilById(Long id) {
        Optional<EtatCivil> etatCivil = etatCivilRepository.findById(id);
        return etatCivil.orElse(null); // Retourne null si l'état civil n'existe pas
    }

    // Mettre à jour un état civil existant
    public EtatCivil updateEtatCivil(Long id, EtatCivil etatCivil) {
        if (etatCivilRepository.existsById(id)) {
            etatCivil.setId(id);
            return etatCivilRepository.save(etatCivil);
        }
        return null; // Si l'ID n'existe pas
    }

    // Supprimer un état civil
    public void deleteEtatCivil(Long id) {
        etatCivilRepository.deleteById(id);
    }
}
