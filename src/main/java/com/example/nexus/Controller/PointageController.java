package com.example.nexus.Controller;

import com.example.nexus.Dto.PointageDTO;
import com.example.nexus.Entitie.Pointage;
import com.example.nexus.Services.PointageService;
import com.example.nexus.mapper.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pointages")
public class PointageController {

    @Autowired
    private PointageService pointageService;

    // Récupérer tous les pointages
    @GetMapping
    public ResponseEntity<List<PointageDTO>> getAllPointages() {
        List<Pointage> pointages = pointageService.getAllPointages();
        return ResponseEntity.ok(ObjectMapper.mapAll(pointages,PointageDTO.class));
    }

    // Récupérer les pointages par date
    @GetMapping("/by-date")
    public ResponseEntity<List<Pointage>> getPointagesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Pointage> pointages = pointageService.getPointagesByDate(date);
        return ResponseEntity.ok(pointages);
    }

    // Récupérer un pointage par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Pointage> getPointageById(@PathVariable Long id) {
        Pointage pointage = pointageService.getPointageById(id);
        return ResponseEntity.ok(pointage);
    }

    // Créer un nouveau pointage
    @PostMapping
    public ResponseEntity<Pointage> createPointage(@RequestBody Pointage pointage) {
        Pointage createdPointage = pointageService.createPointage(pointage);
        return ResponseEntity.ok(createdPointage);
    }

    // Mettre à jour un pointage existant
    @PutMapping("/{id}")
    public ResponseEntity<Pointage> updatePointage(@PathVariable Long id, @RequestBody Pointage pointage) {
        Pointage updatedPointage = pointageService.updatePointage(id, pointage);
        return ResponseEntity.ok(updatedPointage);
    }

    // Supprimer un pointage par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointage(@PathVariable Long id) {
        pointageService.deletePointage(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/import")
    public ResponseEntity<?> importerFichierExcel(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Le fichier est vide.");
            }
            List<PointageDTO> pointages = pointageService.importerFichierExcel(file);
            return ResponseEntity.ok(pointages);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'importation du fichier Excel : " + e.getMessage());
        }
    }
    
}