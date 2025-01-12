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

import com.example.nexus.Entitie.Sexe;
import com.example.nexus.Services.SexeService;

@RestController
@RequestMapping("/sexes")
public class SexeController {

    @Autowired
    private SexeService sexeService;

    @PostMapping
    public Sexe createSexe(@RequestBody Sexe sexe) {
        return sexeService.createSexe(sexe);
    }

    @GetMapping
    public List<Sexe> getAllSexes() {
        return sexeService.getAllSexes();
    }

    @GetMapping("/{id}")
    public Sexe getSexeById(@PathVariable Long id) {
        return sexeService.getSexeById(id);
    }

    @PutMapping("/{id}")
    public Sexe updateSexe(@PathVariable Long id, @RequestBody Sexe sexe) {
        return sexeService.updateSexe(id, sexe);
    }

    @DeleteMapping("/{id}")
    public void deleteSexe(@PathVariable Long id) {
        sexeService.deleteSexe(id);
    }
}
