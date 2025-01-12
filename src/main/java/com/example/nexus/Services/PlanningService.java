package com.example.nexus.Services;



import com.example.nexus.Entitie.Planning;
import com.example.nexus.Repository.PlanningRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanningService {

    @Autowired
    private PlanningRepository planningRepository;

    public Planning createPlanning(Planning planning) {
        return planningRepository.save(planning);
    }

    public List<Planning> getAllPlannings() {
        return planningRepository.findAll();
    }

    public Optional<Planning> getPlanningById(Long id) {
        return planningRepository.findById(id);
    }

    public Planning updatePlanning(Long id, Planning planningDetails) {
        Planning planning = planningRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planning non trouvé"));
        planning.setHoraireDebut(planningDetails.getHoraireDebut());
        planning.setHoraireFin(planningDetails.getHoraireFin());
        return planningRepository.save(planning);
    }

    public void deletePlanning(Long id) {
        planningRepository.deleteById(id);
    }
}
