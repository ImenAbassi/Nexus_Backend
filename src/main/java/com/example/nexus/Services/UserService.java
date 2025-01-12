package com.example.nexus.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nexus.Dto.UserCompagneDTO;
import com.example.nexus.Entitie.Compagne;
import com.example.nexus.Entitie.Enfant;
import com.example.nexus.Entitie.Fonction;
import com.example.nexus.Entitie.MouvementHistorique;
import com.example.nexus.Entitie.Societe;
import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Repository.CompagneRepository;
import com.example.nexus.Repository.MouvementHistoriqueRepository;
import com.example.nexus.Repository.SocieteRepository;
import com.example.nexus.Repository.UserCompagneRepository;
import com.example.nexus.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SocieteRepository societeRepository;
    @Autowired
    private MouvementHistoriqueRepository mouvementHistoriqueRepository;
    @Autowired
    private CompagneRepository compagneRepository;
    @Autowired
    private UserCompagneRepository userCompagneRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserCompagne createUserAndAssignToCompagne(User user, Long compagneId, Fonction fonction, String commentaire,
            LocalDate dateAffectation, LocalDate dateFinAffectation, LocalDateTime dateHeureFormation) {
        // Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Sauvegarder l'utilisateur
        User savedUser = userRepository.save(user);

        // Trouver la Compagne
        Compagne compagne = compagneRepository.findById(compagneId)
                .orElseThrow(() -> new RuntimeException("Compagne not found")); // Créer et sauvegarder UserCompagne
        UserCompagne userCompagne = new UserCompagne();
        userCompagne.setUser(savedUser);
        userCompagne.setCompagne(compagne);
        userCompagne.setFonction(fonction);
        userCompagne.setCommentaire(commentaire);
        userCompagne.setDateAffectation(LocalDate.now());
        userCompagne.setDateFinAffectation(dateFinAffectation);
        userCompagne.setDateHeureFormation(dateHeureFormation);
        return userCompagneRepository.save(userCompagne);
    }

    // Récupérer un UserCompagne par son ID
    public UserCompagne findById(Long userCompagneId) {
        Optional<UserCompagne> userCompagneOpt = userCompagneRepository.findById(userCompagneId);
        return userCompagneOpt.orElse(null); // Retourne null si l'élément n'existe pas
    }
    @Transactional
    public UserCompagne updateUserCompagne(Long userCompagneId, UserCompagneDTO dto) {
        // Trouver l'entité UserCompagne existante
        Optional<UserCompagne> userCompagneOpt = userCompagneRepository.findById(userCompagneId);
        if (!userCompagneOpt.isPresent()) {
            return null;  // Si l'entité UserCompagne n'existe pas, retourner null
        }
    
        // Récupérer l'entité UserCompagne
        UserCompagne userCompagne = userCompagneOpt.get();
    
        // Récupérer l'utilisateur et la compagne associés depuis le DTO
        User user = dto.getUser(); // Utilisateur transmis dans le DTO
        Optional<Compagne> compagneOpt = compagneRepository.findById(dto.getCompagneId());
    
        if (!compagneOpt.isPresent()) {
            return null;  // Si la compagne n'existe pas, retourner null
        }
    
        // Mettre à jour les champs modifiables dans l'entité UserCompagne
        userCompagne.setUser(user);  // Mettre à jour l'utilisateur
        userCompagne.setCompagne(compagneOpt.get());  // Mettre à jour la compagne
        userCompagne.setFonction(dto.getFonction());
        userCompagne.setCommentaire(dto.getCommentaire());
        userCompagne.setDateAffectation(dto.getDateAffectation());
        userCompagne.setDateFinAffectation(dto.getDateFinAffectation());
        userCompagne.setDateHeureFormation(dto.getDateHeureFormation());
    
        // Sauvegarder les modifications dans la base de données
        return userCompagneRepository.save(userCompagne);
    }
    
    @Transactional
    public UserCompagne assignUserToCompagne(Long userId, Long compagneId, Fonction fonction, String commentaire,
            LocalDate dateFinAffectation) {
        // Trouver l'utilisateur
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // Trouver la Compagne
        Compagne compagne = compagneRepository.findById(compagneId)
                .orElseThrow(() -> new RuntimeException("Compagne not found"));
        // Créer et sauvegarder UserCompagne
        UserCompagne userCompagne = new UserCompagne();
        userCompagne.setUser(user);
        userCompagne.setCompagne(compagne);
        userCompagne.setFonction(fonction);
        userCompagne.setCommentaire(commentaire);
        userCompagne.setDateAffectation(LocalDate.now());
        // Utilise la date actuelle du système
        userCompagne.setDateFinAffectation(dateFinAffectation);
        return userCompagneRepository.save(userCompagne);
    }

    @Transactional
    public UserCompagne assignAgentToSupervisorAndProjectLeader(UserCompagneDTO dto) {
        User agent = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Agent not found"));
        User supervisor = null;
        if (dto.getSupervisorId() != null) {
            supervisor = userRepository.findById(dto.getSupervisorId())
                    .orElseThrow(() -> new RuntimeException("Supervisor not found"));
        }
        User projectLeader = userRepository.findById(dto.getProjectLeaderId())
                .orElseThrow(() -> new RuntimeException("Project Leader not found"));
        Compagne compagne = compagneRepository.findById(dto.getCompagneId())
                .orElseThrow(() -> new RuntimeException("Compagne not found"));
        // Create and save UserCompagne
        UserCompagne userCompagne = new UserCompagne();
        userCompagne.setUser(agent);
        userCompagne.setCompagne(compagne);
        userCompagne.setFonction(dto.getFonction());
        userCompagne.setCommentaire(dto.getCommentaire());
        userCompagne.setDateAffectation(dto.getDateAffectation());
        userCompagne.setDateFinAffectation(dto.getDateFinAffectation());
        if (supervisor != null) {
            userCompagne.setSupervisor(supervisor);
        }
        userCompagne.setProjectLeader(projectLeader);
        return userCompagneRepository.save(userCompagne);
    }
/* 
    public List<User> getSupervisorsForProjectLeader(Long projectLeaderId) {
        User projectLeader = userRepository.findById(projectLeaderId)
                .orElseThrow(() -> new RuntimeException("Project Leader not found"));
        return userCompagneRepository.findAllByProjectLeader(projectLeader).stream()
                .filter(uc -> uc.getFonction() == Fonction.SUPERVISEUR).map(UserCompagne::getUser)
                .distinct() // Ajout de .distinct() pour éliminer les doublons
                .collect(Collectors.toList());
    }

    public List<User> getProjectLeadersForCompagne(Long compagneId) {
        Compagne compagne = compagneRepository.findById(compagneId)
                .orElseThrow(() -> new RuntimeException("Compagne not found"));
        return userCompagneRepository.findAllByCompagne(compagne).stream()
                .filter(uc -> uc.getFonction() == Fonction.CHEF_PROJET).map(UserCompagne::getUser)
                .distinct() // Ajout de .distinct() pour éliminer les doublons
                .collect(Collectors.toList());
    }

    public List<User> getAgentsByProjectLeaderAndCompagne(Long projectLeaderId, Long compagneId) {
        return userCompagneRepository.findAllByProjectLeader_IdUserAndCompagne_Id(projectLeaderId, compagneId).stream()
                .filter(uc -> uc.getFonction() == Fonction.AGENT)
                .map(UserCompagne::getUser)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<User> getAgentsForSupervisor(Long supervisorId) {
        User supervisor = userRepository.findById(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));
        return userCompagneRepository.findAllBySupervisor(supervisor).stream()
                .filter(uc -> uc.getFonction() == Fonction.AGENT).map(UserCompagne::getUser)
                .collect(Collectors.toList());
    }

    public List<User> getAgentsByCompagne(Long compagneId) {
        return userCompagneRepository.findAllByCompagne_Id(compagneId).stream()
                .filter(uc -> uc.getFonction() == Fonction.AGENT)
                .map(UserCompagne::getUser)
                .distinct() // Ajout de .distinct() pour éliminer les doublons
                .collect(Collectors.toList());
    }

    public List<User> getSupervisorsByCompagne(Long compagneId) {
        return userCompagneRepository.findAllByCompagne_Id(compagneId).stream()
                .filter(uc -> uc.getFonction() == Fonction.SUPERVISEUR)
                .map(UserCompagne::getUser)
                .distinct() // Ajout de .distinct() pour éliminer les doublons
                .collect(Collectors.toList());
    }
*/
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserCompagneDTO> getUsersWithoutSupervisorOrProjectLeader() {
        // Récupérer les UserCompagnes où le superviseur et le chef de projet sont nuls
        List<UserCompagne> userCompagnes = userCompagneRepository.findBySupervisorIsNullAndProjectLeaderIsNull();
        List<UserCompagneDTO> result = new ArrayList<>();

        // Parcours des UserCompagnes pour enrichir chaque élément
        for (UserCompagne userCompagne : userCompagnes) {
            // Créer un DTO avec l'objet User déjà intégré
            User user = userCompagne.getUser(); // L'utilisateur lié à cette UserCompagne

            UserCompagneDTO userCompagneDTO = new UserCompagneDTO();
            userCompagneDTO.setUser(user); // L'User complet est déjà dans le DTO

            // Ajouter les informations de UserCompagne
            userCompagneDTO.setCompagneId(userCompagne.getCompagne().getId()); // ID de la campagne
            userCompagneDTO.setFonction(userCompagne.getFonction()); // Fonction de l'utilisateur dans la campagne
            userCompagneDTO.setCommentaire(userCompagne.getCommentaire()); // Commentaire sur l'affectation
            userCompagneDTO.setDateHeureFormation(userCompagne.getDateHeureFormation()); // Date et heure de formation
            userCompagneDTO.setDateAffectation(userCompagne.getDateAffectation()); // Date d'affectation
            userCompagneDTO.setDateFinAffectation(userCompagne.getDateFinAffectation()); // Date de fin d'affectation

            // Supervisor et ProjectLeader sont optionnels, donc on vérifie si disponibles
            if (userCompagne.getSupervisor() != null) {
                userCompagneDTO.setSupervisorId(userCompagne.getSupervisor().getIdUser()); // ID du superviseur
            }
            if (userCompagne.getProjectLeader() != null) {
                userCompagneDTO.setProjectLeaderId(userCompagne.getProjectLeader().getIdUser()); // ID du chef de projet
            }

            // Ajouter à la liste de résultats
            result.add(userCompagneDTO);
        }

        return result; // Retourner la liste des UserCompagneDTO
    }

    public Optional<User> getUserById(Long idUser) {
        return userRepository.findById(idUser);
    }

    public User updateUser(Long idUser, User userDetails) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Mettre à jour les champs seulement si les valeurs sont présentes dans
        // userDetails
        if (userDetails.getPrenom() != null)
            user.setPrenom(userDetails.getPrenom());
        if (userDetails.getNom() != null)
            user.setNom(userDetails.getNom());
        if (userDetails.getNomJeuneFille() != null)
            user.setNomJeuneFille(userDetails.getNomJeuneFille());
        if (userDetails.getEtatCivil() != null)
            user.setEtatCivil(userDetails.getEtatCivil());
        if (userDetails.getSexe() != null)
            user.setSexe(userDetails.getSexe());
        if (userDetails.getDateNaissance() != null)
            user.setDateNaissance(userDetails.getDateNaissance());
        if (userDetails.getLieuNaissance() != null)
            user.setLieuNaissance(userDetails.getLieuNaissance());
        if (userDetails.getCin() != null)
            user.setCin(userDetails.getCin());
        if (userDetails.getDelivreeLe() != null)
            user.setDelivreeLe(userDetails.getDelivreeLe());
        if (userDetails.getDelivreeA() != null)
            user.setDelivreeA(userDetails.getDelivreeA());
        if (userDetails.getAdresseMail() != null)
            user.setAdresseMail(userDetails.getAdresseMail());
        if (userDetails.getAdresseMailPro() != null)
            user.setAdresseMailPro(userDetails.getAdresseMailPro());
        if (userDetails.getAdresseCIN() != null)
            user.setAdresseCIN(userDetails.getAdresseCIN());
        if (userDetails.getAdressePersonnelle2() != null)
            user.setAdressePersonnelle2(userDetails.getAdressePersonnelle2());
        if (userDetails.getAdressePersonnelle3() != null)
            user.setAdressePersonnelle3(userDetails.getAdressePersonnelle3());
        if (userDetails.getCodePostal() != null)
            user.setCodePostal(userDetails.getCodePostal());
        if (userDetails.getVille() != null)
            user.setVille(userDetails.getVille());
        if (userDetails.getTelFixe() != null)
            user.setTelFixe(userDetails.getTelFixe());
        if (userDetails.getTelPortable1() != null)
            user.setTelPortable1(userDetails.getTelPortable1());
        if (userDetails.getTelPortable2() != null)
            user.setTelPortable2(userDetails.getTelPortable2());
        if (userDetails.getNationalite() != null)
            user.setNationalite(userDetails.getNationalite());
        if (userDetails.getCnssCnrps() != null)
            user.setCnssCnrps(userDetails.getCnssCnrps());
        if (userDetails.getBanque() != null)
            user.setBanque(userDetails.getBanque());
        if (userDetails.getAgence() != null)
            user.setAgence(userDetails.getAgence());
        if (userDetails.getRib() != null)
            user.setRib(userDetails.getRib());
        if (userDetails.getTypeContrat() != null)
            user.setTypeContrat(userDetails.getTypeContrat());
        if (userDetails.getMatricule() != null)
            user.setMatricule(userDetails.getMatricule());
        if (userDetails.getPhotoCin() != null)
            user.setPhotoCin(userDetails.getPhotoCin());
        if (userDetails.getDescription() != null)
            user.setDescription(userDetails.getDescription());
        if (userDetails.getDateEntree() != null)
            user.setDateEntree(userDetails.getDateEntree());

        if (userDetails.getPhotoDiplome() != null)
            user.setPhotoDiplome(userDetails.getPhotoDiplome());
        if (userDetails.getPhotoProfil() != null)
            user.setPhotoProfil(userDetails.getPhotoProfil());
        if (userDetails.getSalaire() != 0)
            user.setSalaire(userDetails.getSalaire());

        if (userDetails.getEnfants() != null) {
            for (Enfant enfant : userDetails.getEnfants()) {
                enfant.setParent(user); // Associe chaque enfant au parent actuel
            }
            user.getEnfants().clear(); // Vide la liste d'enfants existante
            user.getEnfants().addAll(userDetails.getEnfants()); // Ajoute les nouveaux enfants
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long idUser) {
        userRepository.deleteById(idUser);
    }

   /*  @Transactional
    public void updateUserEtat(Long idUser, EtatUser nouvelEtat, String motif) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Enregistrement dans EtatHistorique
        MouvementHistorique etatHistorique = new MouvementHistorique();
        etatHistorique.setEtat(nouvelEtat);
        etatHistorique.setMotif(motif);
        etatHistorique.setDateAction(LocalDate.now());
        etatHistorique.setUser(user);

        mouvementHistoriqueRepository.save(etatHistorique);

        // Mise à jour de l'état actuel de l'utilisateur
        user.setEtatActuel(nouvelEtat);
        userRepository.save(user);
    }
*/
    public User addEnfantsToUser(Long userId, List<Enfant> enfants) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        for (Enfant enfant : enfants) {
            enfant.setParent(user); // Associe chaque enfant au parent actuel
        }

        user.getEnfants().addAll(enfants); // Ajoute les nouveaux enfants à la liste existante

        return userRepository.save(user); // Sauvegarde l'utilisateur et les enfants associés
    }

    @Transactional
    public User assignUserToSociete(Long userId, Long societeId) {
        // Récupérer l'utilisateur par son identifiant
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Récupérer la société par son identifiant
        Societe societe = societeRepository.findById(societeId)
                .orElseThrow(() -> new RuntimeException("Société non trouvée"));

        // Assigner la société à l'utilisateur
        user.setSociete(societe);
        addMouvementHistorique(user, "Affectation à la societe " + societe.getNom());

        // Sauvegarder les modifications
        return userRepository.save(user);
    }

    public void addMouvementHistorique(User user, String motif) {
        MouvementHistorique mouvement = new MouvementHistorique();
        mouvement.setUser(user);
        mouvement.setMotif(motif);
        mouvement.setDateAction(LocalDate.now());

        mouvementHistoriqueRepository.save(mouvement);
    }

    public List<UserCompagneDTO> getUsersWithSupervisorOrProjectLeader() {
        // Récupérer les UserCompagnes où le superviseur ou le chef de projet est non nul
        List<UserCompagne> userCompagnes = userCompagneRepository.findBySupervisorIsNotNullOrProjectLeaderIsNotNull();
        List<UserCompagneDTO> result = new ArrayList<>();
    
        // Parcours des UserCompagnes pour enrichir chaque élément
        for (UserCompagne userCompagne : userCompagnes) {
            // Créer un DTO avec l'objet User déjà intégré
            User user = userCompagne.getUser(); // L'utilisateur lié à cette UserCompagne
    
            UserCompagneDTO userCompagneDTO = new UserCompagneDTO();
            userCompagneDTO.setUser(user); // L'User complet est déjà dans le DTO
    
            // Ajouter les informations de UserCompagne
            userCompagneDTO.setCompagneId(userCompagne.getCompagne().getId()); // ID de la campagne
            userCompagneDTO.setFonction(userCompagne.getFonction()); // Fonction de l'utilisateur dans la campagne
            userCompagneDTO.setCommentaire(userCompagne.getCommentaire()); // Commentaire sur l'affectation
            userCompagneDTO.setDateHeureFormation(userCompagne.getDateHeureFormation()); // Date et heure de formation
            userCompagneDTO.setDateAffectation(userCompagne.getDateAffectation()); // Date d'affectation
            userCompagneDTO.setDateFinAffectation(userCompagne.getDateFinAffectation()); // Date de fin d'affectation
    
            // Supervisor et ProjectLeader sont optionnels, donc on vérifie si disponibles
            if (userCompagne.getSupervisor() != null) {
                userCompagneDTO.setSupervisorId(userCompagne.getSupervisor().getIdUser()); // ID du superviseur
            }
            if (userCompagne.getProjectLeader() != null) {
                userCompagneDTO.setProjectLeaderId(userCompagne.getProjectLeader().getIdUser()); // ID du chef de projet
            }
    
            // Ajouter à la liste de résultats
            result.add(userCompagneDTO);
        }
    
        return result; // Retourner la liste des UserCompagneDTO
    }
    }
