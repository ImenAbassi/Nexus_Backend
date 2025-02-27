package com.example.nexus.Controller;

import com.example.nexus.Dto.TaxiDTO;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.Taxi;
import com.example.nexus.Entitie.User;
import com.example.nexus.Services.TaxiService;
import com.example.nexus.mapper.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taxi")
@CrossOrigin(origins = "http://localhost:4200")
public class TaxiController {

    @Autowired
    private TaxiService taxiService;

    // Créer une demande de taxi
    @PostMapping("/demander/{userId}")
    public ResponseEntity<TaxiDTO> demanderTaxi(
            @PathVariable Long userId,
            @RequestBody  TaxiDTO taxiDto) {
        System.out.println("Requesting taxi for user: " + userId);
        Taxi taxi = taxiService.demanderTaxi(userId, taxiDto.getLocalisationArrivee(), taxiDto.getHeureDepart());
        return ResponseEntity.ok(ObjectMapper.map(taxi, TaxiDTO.class));
    }

    // Récupérer toutes les demandes de taxi
    @GetMapping("/tous")
    public ResponseEntity<List<TaxiDTO>> obtenirTousLesTaxis() {
        List<Taxi> taxis = taxiService.obtenirTousLesTaxis();
        return ResponseEntity.ok(ObjectMapper.mapAll(taxis, TaxiDTO.class));
    }

    // Récupérer une demande de taxi par son ID
    @GetMapping("/{id}")
    public ResponseEntity<TaxiDTO> obtenirTaxiParId(@PathVariable Long id) {
        Taxi taxi = taxiService.obtenirTaxiParId(id);
        return ResponseEntity.ok(ObjectMapper.map(taxi, TaxiDTO.class));
    }

    // Récupérer les demandes de taxi par utilisateur
    @GetMapping("/utilisateur/{userId}")
    public ResponseEntity<List<TaxiDTO>> obtenirTaxisParUtilisateur(@PathVariable Long userId) {
        List<Taxi> taxis = taxiService.obtenirTaxisParUtilisateur(userId);
        return ResponseEntity.ok(ObjectMapper.mapAll(taxis, TaxiDTO.class));
    }

    // Mettre à jour l'état d'une demande de taxi
    @PutMapping("/{id}/etat")
    public ResponseEntity<TaxiDTO> changerEtatTaxi(
            @PathVariable Long id,
            @RequestParam EtatDemande etat) {
        Taxi taxi = taxiService.changerEtatTaxi(id, etat);
        return ResponseEntity.ok(ObjectMapper.map(taxi, TaxiDTO.class));
    }

    // Supprimer une demande de taxi
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerTaxi(@PathVariable Long id) {
        taxiService.supprimerTaxi(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer toutes les demandes de taxi pour un utilisateur spécifique
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Taxi>> getTaxisByUser(@PathVariable Long userId) {
        User user = new User();
        user.setIdUser(userId);

        List<Taxi> taxis = taxiService.getTaxisByUser(user);
        return ResponseEntity.ok(taxis);
    }

    // Valider une demande de taxi
    @PutMapping("/{id}/validate")
    public ResponseEntity<Taxi> validateDemande(@PathVariable Long id, @RequestParam EtatDemande etatDemande) {
        Taxi updatedTaxi = taxiService.validateDemande(id, etatDemande);
        return ResponseEntity.ok(updatedTaxi);
    }
}