package com.example.nexus.Controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.Avance;
import com.example.nexus.Services.AvanceService;

@RestController
@RequestMapping("/avance")
public class AvanceController {

    @Autowired
    private AvanceService avanceService;

    @PostMapping("/demander/{userId}")
    public ResponseEntity<Avance> creerDemandeAvance(@PathVariable Long userId, @RequestParam BigDecimal montant) {
        Avance avance = avanceService.creerDemandeAvance(userId, montant);
        return ResponseEntity.ok(avance);
    }
    /* 
 @PutMapping("/{id}/etat")
    public Avance changerEtat(@PathVariable Long id, @RequestParam EtatDemande etat) {
        return avanceService.changerEtat(id, etat);
    }*/
    // Ajoutez d'autres endpoints si nécessaire
}
