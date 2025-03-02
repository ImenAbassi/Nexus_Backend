package com.example.nexus.Services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.example.nexus.Entitie.EtatDemande;
import com.example.nexus.Entitie.Pointage;
import com.example.nexus.Entitie.PointageOperation;
import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Repository.CompagneRepository;
import com.example.nexus.Repository.PointageOperationRepository;
import com.example.nexus.Repository.PointageRepository;
import com.example.nexus.Repository.UserCompagneRepository;
import com.example.nexus.Repository.UserRepository;
import com.example.nexus.mapper.ObjectMapper;

import jakarta.transaction.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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

   @Autowired
    private UserCompagneRepository userCompagneRepository;

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
            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 to skip header
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;

                try {
                    // Parse Date and Heure from the Excel file
                    LocalDate date = getCellValueAsDate(row.getCell(0)); // Corrected
                    LocalTime heure = getCellValueAsTime(row.getCell(1)); // Corrected
                    String nom = getCellValueAsString(row.getCell(2));
                    String login = getCellValueAsString(row.getCell(3));
                    String type = getCellValueAsString(row.getCell(4));

                    // Fetch the user by name
                    User user = userRepository.findByNom(nom)
                            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + nom));

                    // Check if a Pointage already exists for the given date and user
                    Pointage pointage = pointageRepository.findByDatePointageAndUser(date, user)
                            .orElseGet(() -> {
                                Pointage newPointage = new Pointage();
                                newPointage.setDatePointage(date);
                                newPointage.setUser(user);
                                newPointage.setOperations(new ArrayList<>());
                                return pointageRepository.save(newPointage);
                            });

                    // Create a new PointageOperation
                    PointageOperation operation = new PointageOperation();
                    operation.setCompagne(login);
                    operation.setHeure(LocalDateTime.of(date, heure)); // Combine date and time
                    operation.setType("CNX".equals(type) ? "ENTREE" : "SORTIE");
                    operation.setPointage(pointage);

                    // Add the operation to the list of operations for the Pointage
                    pointage.getOperations().add(operation);

                    // Save the operation and update the pointage
                    pointageOperationRepository.save(operation);
                    pointage.calculerHeuresTravaillees(); // Calculate worked hours
                    pointageRepository.save(pointage);

                    // Add the pointage to the list
                    pointages.add(pointage);
                } catch (Exception e) {
                    logger.error("Erreur lors du traitement de la ligne {} : {}", i, e.getMessage(), e);
                }
            }
        } catch (IOException e) {
            logger.error("Erreur lors de la lecture du fichier Excel : {}", e.getMessage(), e);
            throw e;
        }

        // Map the list of Pointage entities to DTOs and return
        return ObjectMapper.mapAll(pointages, PointageDTO.class);
    }
    // Helper methods to extract values from cells

    private LocalDate getCellValueAsDate(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.NUMERIC || !DateUtil.isCellDateFormatted(cell)) {
            throw new IllegalArgumentException("La cellule ne contient pas une valeur de date valide.");
        }
        return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private LocalTime getCellValueAsTime(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.NUMERIC) {
            throw new IllegalArgumentException("La cellule ne contient pas une valeur d'heure valide.");
        }
        double timeValue = cell.getNumericCellValue();
        int hours = (int) Math.floor(timeValue * 24);
        int minutes = (int) Math.floor((timeValue * 1440) % 60);
        int seconds = (int) Math.floor((timeValue * 86400) % 60);
        return LocalTime.of(hours, minutes, seconds);
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null)
            return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    public List<Pointage> createPointagesBySupervisor(User supervisor, LocalDate date) {
        // Vérifier si le superviseur existe
        if (supervisor == null || supervisor.getIdUser() == null) {
            throw new IllegalArgumentException("Superviseur non valide.");
        }
        List<UserCompagne> userCompagnes = userCompagneRepository.findBySupervisor(supervisor);

        // Créer un pointage pour chaque utilisateur
        List<Pointage> pointages = new ArrayList<>();
        for (UserCompagne uc : userCompagnes) {
            Pointage pointage = new Pointage();
            pointage.setDatePointage(date);
            pointage.setUser(uc.getUser());
            pointage.setHeuresTravaillees(0L); // Heures travaillées initialisées à 0
            pointage.setEtatDemande(EtatDemande.EN_ATTENTE); // État initialisé à "EN_COURS"
            pointageRepository.save(pointage);
            pointages.add(pointage);
        }

        return pointages;
    }


    // Add operation to a pointage
    public PointageOperation addOperationToPointage(Long pointageId, PointageOperation operation) {
        Pointage pointage = pointageRepository.findById(pointageId).orElseThrow();
        operation.setPointage(pointage);
        return pointageOperationRepository.save(operation);
    }

    // Update an operation
    public PointageOperation updateOperation(Long operationId, PointageOperation operation) {
        PointageOperation existingOperation = pointageOperationRepository.findById(operationId).orElseThrow();
        existingOperation.setCompagne(operation.getCompagne());
        existingOperation.setType(operation.getType());
        existingOperation.setHeure(operation.getHeure());
        return pointageOperationRepository.save(existingOperation);
    }

    // Delete an operation
    public void deleteOperation(Long operationId) {
        pointageOperationRepository.deleteById(operationId);
    }

    @Transactional
    public Pointage validatePointage(Long id) {
        Optional<Pointage> optionalPointage = pointageRepository.findById(id);
        if (optionalPointage.isPresent()) {
            Pointage pointage = optionalPointage.get();
            pointage.setEtatDemande(EtatDemande.APPROUVEE); // Changer l'état à "Validé"
            return pointageRepository.save(pointage);
        }
        return null;
    }

    // Obtenir toutes les opérations d'un pointage
    public List<PointageOperation> getOperationsByPointageId(Long pointageId) {
        return pointageOperationRepository.findByPointageId(pointageId);
    }

    // Exporter les pointages vers un fichier Excel
    public byte[] exportPointagesToExcel(LocalDate date) throws Exception {
        List<Pointage> pointages = (date != null) ? pointageRepository.findByDatePointage(date) : pointageRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Pointages");

            // En-têtes du tableau
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Date Pointage", "Utilisateur", "Heures Travaillées", "État"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Remplir les données
            int rowIndex = 1;
            for (Pointage pointage : pointages) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(pointage.getId());
                row.createCell(1).setCellValue(pointage.getDatePointage().toString());
                row.createCell(2).setCellValue(pointage.getUser().getNom() + " " + pointage.getUser().getPrenom());
                row.createCell(3).setCellValue(pointage.getHeuresTravaillees() / 60.0); // Convertir en heures
                row.createCell(4).setCellValue(pointage.getEtatDemande().name());
            }

            // Générer le fichier Excel en mémoire
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                workbook.write(bos);
                return bos.toByteArray();
            }
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'exportation des pointages : " + e.getMessage(), e);
        }
    }

}

