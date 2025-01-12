package com.example.nexus.Services;


import com.example.nexus.Entitie.JourFerie;
import com.example.nexus.Repository.JourFerieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JourFerieService {

    @Autowired
    private JourFerieRepository jourFerieRepository;

    // Créer un jour férié
    public JourFerie createJourFerie(JourFerie jourFerie) {
        return jourFerieRepository.save(jourFerie);
    }

    // Obtenir tous les jours fériés
    public List<JourFerie> getAllJoursFeries() {
        return jourFerieRepository.findAll();
    }

    // Obtenir un jour férié par ID
    public Optional<JourFerie> getJourFerieById(Long id) {
        return jourFerieRepository.findById(id);
    }

    // Mettre à jour un jour férié
    public JourFerie updateJourFerie(Long id, JourFerie jourFerieDetails) {
        JourFerie jourFerie = jourFerieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jour férié non trouvé"));
        
        jourFerie.setDate(jourFerieDetails.getDate());
        jourFerie.setDescription(jourFerieDetails.getDescription());
        jourFerie.setPays(jourFerieDetails.getPays());
        
        return jourFerieRepository.save(jourFerie);
    }

    // Supprimer un jour férié
    public void deleteJourFerie(Long id) {
        jourFerieRepository.deleteById(id);
    }
}
