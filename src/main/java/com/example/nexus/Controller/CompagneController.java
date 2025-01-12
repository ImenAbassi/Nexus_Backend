package com.example.nexus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.Compagne;
import com.example.nexus.Services.CompagneService;

@RestController
@RequestMapping("/compagnes")
public class CompagneController {

    @Autowired
    private CompagneService compagneService;

    @PostMapping
    public ResponseEntity<Compagne> createCompagne(@RequestBody Compagne compagne) {
        Compagne createdCompagne = compagneService.createCompagne(compagne);
        return new ResponseEntity<>(createdCompagne, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Compagne> getAllCompagnes() {
        return compagneService.getAllCompagnes();
    }

    @GetMapping("/{id}")
    public Compagne getCompagneById(@PathVariable Long id) {
        return compagneService.getCompagneById(id)
                .orElseThrow(() -> new RuntimeException("Compagne non trouvée"));
    }
/* 
    @PutMapping("/{id}")
    public Compagne updateCompagne(@PathVariable Long id, @RequestBody Compagne compagneDetails) {
        return compagneService.updateCompagne(id, compagneDetails);
    }
    */

    @DeleteMapping("/{id}")
    public void deleteCompagne(@PathVariable Long id) {
        compagneService.deleteCompagne(id);
    }

    @PutMapping("/{compagneId}/plannings/{planningId}")
    public ResponseEntity<Compagne> addPlanningsToCompagne(
            @PathVariable Long compagneId,
            @PathVariable Long planningId) {
        compagneService.addPlanningsToCompagne(compagneId, planningId);
        Compagne updatedCompagne = compagneService.getCompagneById(compagneId)
                .orElseThrow(() -> new RuntimeException("Compagne non trouvée"));
        return ResponseEntity.ok(updatedCompagne);
    }

  /*  @GetMapping("/{id}/jours-feries/{annee}")
    public List<LocalDate> getJoursFeries(@PathVariable Long id, @PathVariable int annee) {
        Compagne compagne = compagneService.getCompagneById(id)
                .orElseThrow(() -> new RuntimeException("Compagne non trouvée"));
        return compagneService.getJoursFeriesForCompagne(compagne, annee);
    }
*/
    // Ajouter un jour férié à une campagne
    @PutMapping("/{compagneId}/jours-feries/{jourFerieId}")
    public ResponseEntity<Compagne> addJourFerieToCompagne(
            @PathVariable Long compagneId,
            @PathVariable Long jourFerieId) {
        Compagne updatedCompagne = compagneService.addJourFerieToCompagne(compagneId, jourFerieId);
        return ResponseEntity.ok(updatedCompagne);
    }
}
