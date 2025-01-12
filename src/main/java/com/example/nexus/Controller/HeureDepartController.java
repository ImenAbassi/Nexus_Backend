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

import com.example.nexus.Entitie.HeureDepart;
import com.example.nexus.Services.HeureDepartService;

@RestController
@RequestMapping("/heure-departs")
public class HeureDepartController {

    @Autowired
    private HeureDepartService heureDepartService;

    @PostMapping
    public HeureDepart createHeureDepart(@RequestBody HeureDepart heureDepart) {
        return heureDepartService.createHeureDepart(heureDepart);
    }

    @GetMapping
    public List<HeureDepart> getAllHeureDeparts() {
        return heureDepartService.getAllHeureDeparts();
    }

    @GetMapping("/{id}")
    public HeureDepart getHeureDepartById(@PathVariable Long id) {
        return heureDepartService.getHeureDepartById(id);
    }

    @PutMapping("/{id}")
    public HeureDepart updateHeureDepart(@PathVariable Long id, @RequestBody HeureDepart heureDepart) {
        return heureDepartService.updateHeureDepart(id, heureDepart);
    }

    @DeleteMapping("/{id}")
    public void deleteHeureDepart(@PathVariable Long id) {
        heureDepartService.deleteHeureDepart(id);
    }
}
