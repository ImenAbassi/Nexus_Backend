package com.example.nexus.Controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.PlanningUser;
import com.example.nexus.Services.PlanningUserService;

@RestController
@RequestMapping("/planning-user")
public class PlanningUserController {

    @Autowired
    private PlanningUserService planningUserService;

    @PostMapping("/ajouter")
    public PlanningUser ajouterPlanning(@RequestBody PlanningUser planning) {
        return planningUserService.ajouterPlanning(planning);
    }

    @GetMapping("/user/{userId}")
    public List<PlanningUser> getPlanningParUtilisateur(@PathVariable Long userId) {
        return planningUserService.getPlanningParUtilisateur(userId);
    }

    @GetMapping("/periode")
    public List<PlanningUser> getPlanningParPeriode(
            @RequestParam("debut") LocalTime debut,
            @RequestParam("fin") LocalTime fin) {
        return planningUserService.getPlanningParPeriode(debut, fin);
    }

    @DeleteMapping("/supprimer/{id}")
    public void supprimerPlanning(@PathVariable Long id) {
        planningUserService.supprimerPlanning(id);
    }
}
