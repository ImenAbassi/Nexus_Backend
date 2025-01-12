package com.example.nexus.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Enfant;
import com.example.nexus.Repository.EnfantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnfantService {

    @Autowired
    private EnfantRepository enfantRepository;

    public Enfant createEnfant(Enfant enfant) {
        return enfantRepository.save(enfant);
    }

    public List<Enfant> getAllEnfants() {
        return enfantRepository.findAll();
    }

    public Optional<Enfant> getEnfantById(Integer idEnfant) {
        return enfantRepository.findById(idEnfant);
    }

    public Enfant updateEnfant(Integer idEnfant, Enfant enfantDetails) {
        Enfant enfant = enfantRepository.findById(idEnfant)
                .orElseThrow(() -> new RuntimeException("Enfant non trouvé"));
        enfant.setPrenom(enfantDetails.getPrenom());
        enfant.setSexe(enfantDetails.getSexe());
        enfant.setDateNaissance(enfantDetails.getDateNaissance());
        enfant.setParent(enfantDetails.getParent()); // Mise à jour du parent si nécessaire
        return enfantRepository.save(enfant);
    }

    public void deleteEnfant(Integer idEnfant) {
        enfantRepository.deleteById(idEnfant);
    }
}