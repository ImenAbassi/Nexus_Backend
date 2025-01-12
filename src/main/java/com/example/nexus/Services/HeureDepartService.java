package com.example.nexus.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.HeureDepart;
import com.example.nexus.Repository.HeureDepartRepository;

@Service
public class HeureDepartService {

    @Autowired
    private HeureDepartRepository heureDepartRepository;

    public HeureDepart createHeureDepart(HeureDepart heureDepart) {
        return heureDepartRepository.save(heureDepart);
    }

    public List<HeureDepart> getAllHeureDeparts() {
        return heureDepartRepository.findAll();
    }

    public HeureDepart getHeureDepartById(Long id) {
        return heureDepartRepository.findById(id).orElse(null);
    }

    public HeureDepart updateHeureDepart(Long id, HeureDepart heureDepartDetails) {
        HeureDepart heureDepart = heureDepartRepository.findById(id).orElse(null);
        if (heureDepart != null) {
            heureDepart.setHeure(heureDepartDetails.getHeure());
            return heureDepartRepository.save(heureDepart);
        }
        return null;
    }

    public void deleteHeureDepart(Long id) {
        heureDepartRepository.deleteById(id);
    }
}
