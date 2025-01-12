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

import com.example.nexus.Entitie.EtatCivil;
import com.example.nexus.Services.EtatCivilService;

@RestController
@RequestMapping("/etat-civils")
public class EtatCivilController {

    @Autowired
    private EtatCivilService etatCivilService;

    @PostMapping
    public EtatCivil createEtatCivil(@RequestBody EtatCivil etatCivil) {
        return etatCivilService.createEtatCivil(etatCivil);
    }

    @GetMapping
    public List<EtatCivil> getAllEtatCivils() {
        return etatCivilService.getAllEtatCivils();
    }

    @GetMapping("/{id}")
    public EtatCivil getEtatCivilById(@PathVariable Long id) {
        return etatCivilService.getEtatCivilById(id);
    }

    @PutMapping("/{id}")
    public EtatCivil updateEtatCivil(@PathVariable Long id, @RequestBody EtatCivil etatCivil) {
        return etatCivilService.updateEtatCivil(id, etatCivil);
    }

    @DeleteMapping("/{id}")
    public void deleteEtatCivil(@PathVariable Long id) {
        etatCivilService.deleteEtatCivil(id);
    }
}
