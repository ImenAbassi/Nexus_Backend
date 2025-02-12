package com.example.nexus.Services;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.nexus.Dto.PointageDTO;
import com.example.nexus.Entitie.Pointage;
import com.example.nexus.Entitie.PointageOperation;
import com.example.nexus.Entitie.User;
import com.example.nexus.Repository.PointageOperationRepository;
import com.example.nexus.Repository.PointageRepository;
import com.example.nexus.Repository.UserRepository;
import com.example.nexus.mapper.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PointageService {
    private static final Logger logger = LoggerFactory.getLogger(PointageService.class);

    @Autowired
    private PointageRepository pointageRepository;

      @Autowired
    private UserRepository userRepository;

    @Autowired
    private PointageOperationRepository pointageOperationRepository;

    // Récupérer tous les pointages
    public List<Pointage> getAllPointages() {
        return pointageRepository.findAll();
    }

    // Récupérer les pointages par date
    public List<Pointage> getPointagesByDate(LocalDate date) {
        return pointageRepository.findByDatePointage(date);
    }

    // Récupérer un pointage par son ID
    public Pointage getPointageById(Long id) {
        Optional<Pointage> pointage = pointageRepository.findById(id);
        return pointage.orElseThrow(() -> new RuntimeException("Pointage non trouvé"));
    }

    // Créer un nouveau pointage
    public Pointage createPointage(Pointage pointage) {
        pointage.calculerHeuresTravaillees(); // Calculer les heures travaillées avant de sauvegarder
        return pointageRepository.save(pointage);
    }

    // Mettre à jour un pointage existant
    public Pointage updatePointage(Long id, Pointage pointageDetails) {
        Pointage pointage = getPointageById(id);
        pointage.setDatePointage(pointageDetails.getDatePointage());
        pointage.setOperations(pointageDetails.getOperations());
        pointage.setUser(pointageDetails.getUser());
        pointage.setPlanningUser(pointageDetails.getPlanningUser());
        pointage.calculerHeuresTravaillees(); // Recalculer les heures travaillées
        return pointageRepository.save(pointage);
    }

    // Supprimer un pointage par son ID
    public void deletePointage(Long id) {
        pointageRepository.deleteById(id);
    }

 
public List<PointageDTO> importerFichierExcel(MultipartFile file) throws IOException {
    List<Pointage> pointages = new ArrayList<>();
    try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
        Sheet sheet = workbook.getSheetAt(0); // Prendre la première feuille

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            try {
                LocalDate date = LocalDate.now();
                LocalDateTime heure = LocalDateTime.now();
                String nom = getCellValueAsString(row.getCell(2));
                String login = getCellValueAsString(row.getCell(3));
                String type = getCellValueAsString(row.getCell(4));

                User user = userRepository.findByNom(nom)
                        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + nom));

                Pointage pointage = pointageRepository.findByDatePointageAndUser(date, user)
                        .orElseGet(() -> {
                            Pointage newPointage = new Pointage();
                            newPointage.setDatePointage(date);
                            newPointage.setUser(user);
                            newPointage.setOperations(new ArrayList<>());
                            return pointageRepository.save(newPointage);
                        });
                        List<PointageOperation> operations = pointage.getOperations();
                PointageOperation operation = new PointageOperation();
                operation.setCompagne(login);
                operation.setHeure(heure);
                operation.setType("CNX".equals(type) ? "ENTREE" : "SORTIE");
                operation.setPointage(pointage);
                operations.add(operation);
                pointage.setOperations(operations);
                pointageOperationRepository.save(operation);

                pointage.calculerHeuresTravaillees();
                pointageRepository.save(pointage);

                pointages.add(pointage);
            } catch (Exception e) {
                logger.error("Erreur lors du traitement de la ligne {} : {}", i, e.getMessage(), e);
            }
        }
    } catch (IOException e) {
        logger.error("Erreur lors de la lecture du fichier Excel : {}", e.getMessage(), e);
        throw e;
    }
    return ObjectMapper.mapAll(pointages, PointageDTO.class);
}

private String getCellValueAsString(Cell cell) {
    if (cell == null) {
        return "";
    }

    switch (cell.getCellType()) {
        case STRING:
            return cell.getStringCellValue();
        case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getLocalDateTimeCellValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } else {
                return String.valueOf((int) cell.getNumericCellValue());
            }
        case BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        case FORMULA:
            return cell.getCellFormula();
        default:
            return "";
    }
}

private LocalDate convertToLocalDate(String dateStr) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    } catch (DateTimeParseException e) {
        logger.error("Erreur de format de date : {}", dateStr, e);
        throw new RuntimeException("Format de date invalide : " + dateStr + ". Le format attendu est dd/MM/yyyy", e);
    }
}

private LocalDateTime convertToLocalDateTime(String dateTimeStr) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.parse(dateTimeStr, formatter);
    } catch (DateTimeParseException e) {
        logger.error("Erreur de format d'heure : {}", dateTimeStr, e);
        throw new RuntimeException("Format d'heure invalide : " + dateTimeStr + ". Le format attendu est HH:mm:ss", e);
    }
}


}

