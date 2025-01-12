package com.example.nexus.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.JourSemaine;
import com.example.nexus.Repository.JourSemaineRepository;

@Service
public class JourSemaineService {

    @Autowired
    private JourSemaineRepository jourSemaineRepository;

    // Méthode pour récupérer tous les jours de la semaine
    public List<JourSemaine> getAllJourSemaine() {
        return jourSemaineRepository.findAll();
    }

    // Méthode pour récupérer un jour de la semaine par son ID
    public Optional<JourSemaine> getJourSemaineById(Long id) {
        return jourSemaineRepository.findById(id);
    }

    // Méthode pour ajouter un nouveau jour de la semaine
    public JourSemaine addJourSemaine(JourSemaine jourSemaine) {
        return jourSemaineRepository.save(jourSemaine);
    }

    // Méthode pour mettre à jour un jour de la semaine
    public JourSemaine updateJourSemaine(Long id, JourSemaine jourSemaineDetails) {
        Optional<JourSemaine> jourSemaineOptional = jourSemaineRepository.findById(id);
        if (jourSemaineOptional.isPresent()) {
            JourSemaine jourSemaine = jourSemaineOptional.get();
            jourSemaine.setNom(jourSemaineDetails.getNom());
            return jourSemaineRepository.save(jourSemaine);
        }
        return null;
    }

    // Méthode pour supprimer un jour de la semaine par son ID
    public void deleteJourSemaine(Long id) {
        jourSemaineRepository.deleteById(id);
    }
}
