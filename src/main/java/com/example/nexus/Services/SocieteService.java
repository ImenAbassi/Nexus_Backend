package com.example.nexus.Services;



import com.example.nexus.Entitie.Societe;
import com.example.nexus.Repository.SocieteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocieteService {

    @Autowired
    private SocieteRepository societeRepository;

    public Societe createSociete(Societe societe) {
        return societeRepository.save(societe);
    }

    public List<Societe> getAllSocietes() {
        return societeRepository.findAll();
    }

    public Optional<Societe> getSocieteById(Long id) {
        return societeRepository.findById(id);
    }

    public Societe updateSociete(Long id, Societe societeDetails) {
        Societe societe = societeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Societe non trouvée"));
        societe.setNom(societeDetails.getNom());
        return societeRepository.save(societe);
    }

    public void deleteSociete(Long id) {
        societeRepository.deleteById(id);
    }
}
