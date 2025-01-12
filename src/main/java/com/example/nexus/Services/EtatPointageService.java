package com.example.nexus.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.EtatPointage;
import com.example.nexus.Repository.EtatPointageRepository;

@Service
public class EtatPointageService {

    @Autowired
    private EtatPointageRepository etatPointageRepository;

    public List<EtatPointage> getAllEtatPointages() {
        return etatPointageRepository.findAll();
    }

    public Optional<EtatPointage> getEtatPointageById(Long id) {
        return etatPointageRepository.findById(id);
    }

    public EtatPointage createEtatPointage(EtatPointage etatPointage) {
        return etatPointageRepository.save(etatPointage);
    }

    public EtatPointage updateEtatPointage(Long id, EtatPointage updatedEtatPointage) {
        if (etatPointageRepository.existsById(id)) {
            updatedEtatPointage.setId(id);
            return etatPointageRepository.save(updatedEtatPointage);
        }
        return null;  // Ou vous pouvez lever une exception personnalisée si nécessaire
    }

    public void deleteEtatPointage(Long id) {
        etatPointageRepository.deleteById(id);
    }
}
