package com.example.nexus.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.Fonction;
import com.example.nexus.Services.FonctionService;

@RestController
@RequestMapping("/fonctions")
public class FonctionController {

    @Autowired
    private FonctionService fonctionService;

    @PostMapping
    public Fonction createFonction(@RequestBody Fonction fonction) {
        return fonctionService.createFonction(fonction);
    }

    @GetMapping
    public List<Fonction> getAllFonctions() {
        return fonctionService.getAllFonctions();
    }

    @GetMapping("/{id}")
    public Fonction getFonctionById(@PathVariable Long id) {
        return fonctionService.getFonctionById(id);
    }

    @PutMapping("/{id}")
    public Fonction updateFonction(@PathVariable Long id, @RequestBody Fonction fonction) {
        return fonctionService.updateFonction(id, fonction);
    }

    @DeleteMapping("/{id}")
    public void deleteFonction(@PathVariable Long id) {
        fonctionService.deleteFonction(id);
    }
}
