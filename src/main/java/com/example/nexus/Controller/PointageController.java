package com.example.nexus.Controller;

import com.example.nexus.Dto.PointageDTO;
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.Pointage;
import com.example.nexus.Entitie.PointageOperation;
import com.example.nexus.Entitie.User;
import com.example.nexus.Services.PointageService;
import com.example.nexus.mapper.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pointages")
@CrossOrigin(origins = "http://localhost:4200")
public class PointageController {

    @Autowired
    private PointageService pointageService;

    // Récupérer tous les pointages
    @GetMapping
    public ResponseEntity<List<PointageDTO>> getAllPointages() {
        List<Pointage> pointages = pointageService.getAllPointages();
        return ResponseEntity.ok(ObjectMapper.mapAll(pointages, PointageDTO.class));
    }

    @PostMapping("/valider/{id}")
    public ResponseEntity<Map<String, String>> valider(
            @PathVariable Long id,
            @RequestParam EtatDemande etat) { // Utilisez @RequestParam pour récupérer l'état
        try {
            pointageService.valider(id, etat); // Passez l'état dynamiquement
            Map<String, String> response = new HashMap<>();
            response.put("message", "Demande validée par le superviseur avec l'état : " + etat);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "État invalide : " + etat);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/supervisor/{supervisorId}")
    public ResponseEntity<List<PointageDTO>> getAllBySupervisor(@PathVariable Long supervisorId) {
        List<Pointage> pointages = pointageService.getAllBySupervisor(supervisorId);
        return ResponseEntity.ok(ObjectMapper.mapAll(pointages, PointageDTO.class));
    }

    @GetMapping("/project-leader/{projectLeaderId}")
    public ResponseEntity<List<PointageDTO>> getAllByProjectLeader(@PathVariable Long projectLeaderId) {
        List<Pointage> pointages = pointageService.getAllByProjectLeader(projectLeaderId);
        return ResponseEntity.ok(ObjectMapper.mapAll(pointages, PointageDTO.class));
    }

    @PostMapping("/withoperation")
    public ResponseEntity<Pointage> createPointageWithOperations(@RequestBody Pointage pointage) {
        Pointage createdPointage = pointageService.createPointageWithOperations(pointage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPointage);
    }

    @PutMapping("/withoperation/{id}")
    public ResponseEntity<Pointage> updatePointageWithOperations(
            @PathVariable Long id,
            @RequestBody Pointage pointage) {
        Pointage updatedPointage = pointageService.updatePointageWithOperations(id, pointage);
        if (updatedPointage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPointage);
    }

    // Créer des pointages pour un superviseur donné
    @PostMapping("/create-by-supervisor/{id}")
    public ResponseEntity<List<PointageDTO>> createPointagesBySupervisor(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User supervisor = new User();
        supervisor.setIdUser(id);
        List<Pointage> createdPointages = pointageService.createPointagesBySupervisor(supervisor, date);
        return ResponseEntity.ok(ObjectMapper.mapAll(createdPointages, PointageDTO.class));
    }

    // Ajouter une opération à un pointage
    @PostMapping("/{pointageId}/operations")
    public ResponseEntity<PointageOperation> addOperationToPointage(
            @PathVariable Long pointageId,
            @RequestBody PointageOperation operation) {
        PointageOperation addedOperation = pointageService.addOperationToPointage(pointageId, operation);
        return ResponseEntity.ok(addedOperation);
    }

    // Mettre à jour une opération
    @PutMapping("/operations/{operationId}")
    public ResponseEntity<PointageOperation> updateOperation(
            @PathVariable Long operationId,
            @RequestBody PointageOperation operation) {
        PointageOperation updatedOperation = pointageService.updateOperation(operationId, operation);
        return ResponseEntity.ok(updatedOperation);
    }

    // Supprimer une opération
    @DeleteMapping("/operations/{operationId}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long operationId) {
        pointageService.deleteOperation(operationId);
        return ResponseEntity.noContent().build();
    }

    // Récupérer les pointages par date
    /*
     * @GetMapping("/by-date")
     * public ResponseEntity<List<Pointage>> getPointagesByDate(
     * 
     * @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
     * ) {
     * List<Pointage> pointages = pointageService.getPointagesByDate(date);
     * return ResponseEntity.ok(pointages);
     * }
     */

    // Récupérer un pointage par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Pointage> getPointageById(@PathVariable Long id) {
        Pointage pointage = pointageService.getPointageById(id);
        if (pointage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pointage);
    }

    // Créer un nouveau pointage
    @PostMapping
    public ResponseEntity<Pointage> createPointage(@RequestBody Pointage pointage) {
        Pointage createdPointage = pointageService.createPointage(pointage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPointage);
    }

    // Mettre à jour un pointage existant
    @PutMapping("/{id}")
    public ResponseEntity<Pointage> updatePointage(
            @PathVariable Long id,
            @RequestBody Pointage pointage) {
        Pointage updatedPointage = pointageService.updatePointage(id, pointage);
        if (updatedPointage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPointage);
    }

    // Supprimer un pointage par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointage(@PathVariable Long id) {
        pointageService.deletePointage(id);
        return ResponseEntity.noContent().build();
    }

    // Importer un fichier Excel
    /*
     * @PostMapping("/import")
     * public ResponseEntity<?> importerFichierExcel(@RequestParam("file")
     * MultipartFile file) {
     * try {
     * if (file.isEmpty()) {
     * return ResponseEntity.badRequest().body("Le fichier est vide.");
     * }
     * List<PointageDTO> pointages = pointageService.importerFichierExcel(file);
     * return ResponseEntity.ok(pointages);
     * } catch (IOException e) {
     * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
     * .body("Erreur lors de l'importation du fichier Excel : " + e.getMessage());
     * }
     * }
     */

    // Valider un pointage
    @PostMapping("/{id}/validate")
    public ResponseEntity<Pointage> validatePointage(@PathVariable Long id) {
        Pointage pointage = pointageService.validatePointage(id);
        if (pointage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pointage);
    }

    // Obtenir toutes les opérations d'un pointage
    /*
     * @GetMapping("/{pointageId}/operations")
     * public ResponseEntity<List<PointageOperation>>
     * getOperationsByPointageId(@PathVariable Long pointageId) {
     * List<PointageOperation> operations =
     * pointageService.getOperationsByPointageId(pointageId);
     * return ResponseEntity.ok(operations);
     * }
     */

    // Exporter les pointages vers un fichier Excel
    /*
     * @GetMapping("/export/excel")
     * public ResponseEntity<byte[]> exportPointagesToExcel(
     * 
     * @RequestParam(required = false) @DateTimeFormat(iso =
     * DateTimeFormat.ISO.DATE) LocalDate date
     * ) {
     * try {
     * byte[] excelData = pointageService.exportPointagesToExcel(date);
     * return ResponseEntity.ok()
     * .header("Content-Disposition", "attachment; filename=pointages.xlsx")
     * .body(excelData);
     * } catch (Exception e) {
     * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
     * .body(null);
     * }
     * }
     */
}