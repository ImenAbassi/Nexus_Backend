package com.example.nexus.Services;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.PlanningUser;
import com.example.nexus.Repository.PlanningUserRepository;

@Service
public class PlanningUserService {

    @Autowired
    private PlanningUserRepository planningUserRepository;

    public PlanningUser ajouterPlanning(PlanningUser planning) {
        return planningUserRepository.save(planning);
    }

    public List<PlanningUser> getPlanningParUtilisateur(Long userId) {
        return planningUserRepository.findByUserIdUser(userId);
    }

    public List<PlanningUser> getPlanningParPeriode(LocalTime debut, LocalTime fin) {
        return planningUserRepository.findByHeureDebutBetween(debut, fin);
    }

    public void supprimerPlanning(Long id) {
        planningUserRepository.deleteById(id);
    }
}
