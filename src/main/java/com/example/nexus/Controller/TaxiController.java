package com.example.nexus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.Taxi;
import com.example.nexus.Services.TaxiService;

@RequestMapping("/taxi")
public class TaxiController {

    @Autowired
    private TaxiService taxiService;

    @PostMapping("/demander/{userId}")
    public ResponseEntity<Taxi> demanderTaxi(@PathVariable Long userId, @RequestParam Long heureDepartId) {
        Taxi taxi = taxiService.demanderTaxi(userId, heureDepartId);
        return ResponseEntity.ok(taxi);
    }

    @PutMapping("/{id}/etat")
    public Taxi changerEtat(@PathVariable Long id, @RequestParam EtatDemande etat) {
        return taxiService.changerEtat(id, etat);
    }
}
