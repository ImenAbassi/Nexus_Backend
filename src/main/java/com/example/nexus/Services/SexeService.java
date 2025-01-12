package com.example.nexus.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Sexe;
import com.example.nexus.Repository.SexeRepository;

@Service
public class SexeService {

    @Autowired
    private SexeRepository sexeRepository;

    public Sexe createSexe(Sexe sexe) {
        return sexeRepository.save(sexe);
    }

    public List<Sexe> getAllSexes() {
        return sexeRepository.findAll();
    }

    public Sexe getSexeById(Long id) {
        Optional<Sexe> sexe = sexeRepository.findById(id);
        return sexe.orElse(null);
    }

    public Sexe updateSexe(Long id, Sexe sexeDetails) {
        Sexe sexe = sexeRepository.findById(id).orElse(null);
        if (sexe != null) {
            sexe.setNom(sexeDetails.getNom());
            return sexeRepository.save(sexe);
        }
        return null;
    }

    public void deleteSexe(Long id) {
        sexeRepository.deleteById(id);
    }
}
