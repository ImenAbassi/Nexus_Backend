package com.example.nexus.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.JourFerie;
import com.example.nexus.Services.JourFerieService;

@RestController
@RequestMapping("/joursferies")
public class JourFerieController {

    @Autowired
    private JourFerieService jourFerieService;

    // Créer un jour férié
    @PostMapping
    public JourFerie createJourFerie(@RequestBody JourFerie jourFerie) {
        return jourFerieService.createJourFerie(jourFerie);
    }

    // Obtenir tous les jours fériés
    @GetMapping
    public List<JourFerie> getAllJoursFeries() {
        List<JourFerie> joursFeries = jourFerieService.getAllJoursFeries();
        System.out.println("Nombre de jours fériés trouvés : " + joursFeries.size());
        return joursFeries;
    }
    

    // Obtenir un jour férié par ID
    @GetMapping("/{id}")
    public Optional<JourFerie> getJourFerieById(@PathVariable Long id) {
        return jourFerieService.getJourFerieById(id);
    }

    // Mettre à jour un jour férié
    @PutMapping("/{id}")
    public JourFerie updateJourFerie(@PathVariable Long id, @RequestBody JourFerie jourFerieDetails) {
        return jourFerieService.updateJourFerie(id, jourFerieDetails);
    }

    // Supprimer un jour férié
    @DeleteMapping("/{id}")
    public void deleteJourFerie(@PathVariable Long id) {
        jourFerieService.deleteJourFerie(id);
    }
}