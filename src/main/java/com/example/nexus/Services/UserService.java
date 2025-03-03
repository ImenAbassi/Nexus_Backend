package com.example.nexus.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nexus.Dto.UserCompagneDTO;
import com.example.nexus.Entitie.Compagne;
import com.example.nexus.Entitie.Enfant;
import com.example.nexus.Entitie.Fonction;
import com.example.nexus.Entitie.MouvementHistorique;
import com.example.nexus.Entitie.Societe;
import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Exceptions.CompagneNotFoundException;
import com.example.nexus.Exceptions.SocieteNotFoundException;
import com.example.nexus.Exceptions.UserCompagneNotFoundException;
import com.example.nexus.Exceptions.UserNotFoundException;
import com.example.nexus.Repository.CompagneRepository;
import com.example.nexus.Repository.FonctionRepository;
import com.example.nexus.Repository.MouvementHistoriqueRepository;
import com.example.nexus.Repository.SocieteRepository;
import com.example.nexus.Repository.UserCompagneRepository;
import com.example.nexus.Repository.UserRepository;

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
    private FonctionRepository fonctionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserCompagne createUserAndAssignToCompagne(
            User user,
            Long compagneId,
            Long fonctionId,
            String commentaire,
            LocalDate dateAffectation,
            LocalDate dateFinAffectation,
            LocalDateTime dateHeureFormation) {

        if (user == null || user.getCin() == null || user.getNom() == null || user.getPrenom() == null) {
            throw new RuntimeException("Les informations de l'utilisateur sont incomplètes.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        Compagne compagne = compagneRepository.findById(compagneId)
                .orElseThrow(() -> new CompagneNotFoundException("Compagne non trouvée."));

        Fonction fonction = fonctionRepository.findById(fonctionId)
                .orElseThrow(() -> new RuntimeException("Fonction non trouvée."));

        UserCompagne userCompagne = new UserCompagne();
        userCompagne.setUser(savedUser);
        userCompagne.setCompagne(compagne);
        userCompagne.setFonction(fonction);
        userCompagne.setCommentaire(commentaire);
        userCompagne.setDateAffectation(dateAffectation != null ? dateAffectation : LocalDate.now());
        userCompagne.setDateFinAffectation(dateFinAffectation);
        userCompagne.setDateHeureFormation(dateHeureFormation);

        return userCompagneRepository.save(userCompagne);
    }

    public UserCompagne findById(Long userCompagneId) {
        return userCompagneRepository.findById(userCompagneId)
                .orElseThrow(() -> new UserCompagneNotFoundException("UserCompagne non trouvée."));
    }

    public User findByIdUser(Long userCompagneId) {
        return userRepository.findById(userCompagneId)
                .orElseThrow(() -> new UserCompagneNotFoundException("UserCompagne non trouvée."));
    }

    @Transactional
    public UserCompagne updateUserCompagne(Long userCompagneId, UserCompagneDTO dto) {
        // Fetch the existing UserCompagne entity
        UserCompagne userCompagne = userCompagneRepository.findById(userCompagneId)
                .orElseThrow(() -> new UserCompagneNotFoundException("UserCompagne non trouvée."));

        // Fetch the existing User entity
        User existingUser = userCompagne.getUser();

        // Update the existing User entity with new data
        existingUser.setCin(dto.getUser().getCin());
        existingUser.setNom(dto.getUser().getNom());
        existingUser.setPrenom(dto.getUser().getPrenom());
        existingUser.setTelPortable1(dto.getUser().getTelPortable1());
        existingUser.setAdresseMail(dto.getUser().getAdresseMail());
        existingUser.setPassword(passwordEncoder.encode(dto.getUser().getPassword()));

        // Save the updated User entity
        existingUser = userRepository.save(existingUser);

        // Fetch the Compagne entity
        Compagne compagne = compagneRepository.findById(dto.getCompagneId())
                .orElseThrow(() -> new CompagneNotFoundException("Compagne non trouvée."));

        // Fetch the Fonction entity
        Fonction fonction = fonctionRepository.findById(dto.getFonctionId())
                .orElseThrow(() -> new RuntimeException("Fonction non trouvée."));

        // Update the UserCompagne entity
        userCompagne.setUser(existingUser);
        userCompagne.setCompagne(compagne);
        userCompagne.setFonction(fonction);
        userCompagne.setCommentaire(dto.getCommentaire());
        userCompagne.setDateAffectation(dto.getDateAffectation());
        userCompagne.setDateFinAffectation(dto.getDateFinAffectation());
        userCompagne.setDateHeureFormation(dto.getDateHeureFormation());

        // Save the updated UserCompagne entity
        return userCompagneRepository.save(userCompagne);
    }

    @Transactional
    public UserCompagne assignUserToCompagne(Long userId, Long compagneId, Long fonctionId, String commentaire,
            LocalDate dateFinAffectation) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));

        Compagne compagne = compagneRepository.findById(compagneId)
                .orElseThrow(() -> new CompagneNotFoundException("Compagne non trouvée"));

        Fonction fonction = fonctionRepository.findById(fonctionId)
                .orElseThrow(() -> new RuntimeException("Fonction non trouvée"));

        UserCompagne userCompagne = new UserCompagne();
        userCompagne.setUser(user);
        userCompagne.setCompagne(compagne);
        userCompagne.setFonction(fonction); // Utilisez l'objet Fonction
        userCompagne.setCommentaire(commentaire);
        userCompagne.setDateAffectation(LocalDate.now());
        userCompagne.setDateFinAffectation(dateFinAffectation);

        return userCompagneRepository.save(userCompagne);
    }

    @Transactional
    public UserCompagne assignAgentToSupervisorAndProjectLeader(UserCompagneDTO dto) {
        User agent = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Agent non trouvé"));

        User supervisor = null;
        if (dto.getSupervisorId() != null) {
            supervisor = userRepository.findById(dto.getSupervisorId())
                    .orElseThrow(() -> new UserNotFoundException("Superviseur non trouvé"));
        }

        User projectLeader = userRepository.findById(dto.getProjectLeaderId())
                .orElseThrow(() -> new UserNotFoundException("Chef de projet non trouvé"));

        Compagne compagne = compagneRepository.findById(dto.getCompagneId())
                .orElseThrow(() -> new CompagneNotFoundException("Compagne non trouvée"));

        Fonction fonction = fonctionRepository.findById(dto.getFonctionId())
                .orElseThrow(() -> new RuntimeException("Fonction non trouvée"));

        UserCompagne userCompagne = new UserCompagne();
        userCompagne.setUser(agent);
        userCompagne.setCompagne(compagne);
        userCompagne.setFonction(fonction); // Utilisez l'objet Fonction
        userCompagne.setCommentaire(dto.getCommentaire());
        userCompagne.setDateAffectation(dto.getDateAffectation());
        userCompagne.setDateFinAffectation(dto.getDateFinAffectation());

        if (supervisor != null) {
            userCompagne.setSupervisor(supervisor);
        }
        userCompagne.setProjectLeader(projectLeader);

        return userCompagneRepository.save(userCompagne);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserCompagneDTO> getUsersWithoutSupervisorOrProjectLeader() {
        List<UserCompagne> userCompagnes = userCompagneRepository.findBySupervisorIsNullAndProjectLeaderIsNull();
        List<UserCompagneDTO> result = new ArrayList<>();

        for (UserCompagne userCompagne : userCompagnes) {
            UserCompagneDTO dto = new UserCompagneDTO();
            dto.setId(userCompagne.getId());
            dto.setUser(userCompagne.getUser());
            dto.setCompagneId(userCompagne.getCompagne().getId());
            dto.setFonctionId(userCompagne.getFonction().getId());
            dto.setFonction(userCompagne.getFonction());
            dto.setCommentaire(userCompagne.getCommentaire());
            dto.setDateHeureFormation(userCompagne.getDateHeureFormation());
            dto.setDateAffectation(userCompagne.getDateAffectation());
            dto.setDateFinAffectation(userCompagne.getDateFinAffectation());

            if (userCompagne.getSupervisor() != null) {
                dto.setSupervisorId(userCompagne.getSupervisor().getIdUser());
            }
            if (userCompagne.getProjectLeader() != null) {
                dto.setProjectLeaderId(userCompagne.getProjectLeader().getIdUser());
            }

            result.add(dto);
        }

        return result;
    }

    public Optional<User> getUserById(Long idUser) {
        return userRepository.findById(idUser);
    }

    public User updateUser(Long idUser, User userDetails) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));

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
                enfant.setParent(user);
            }
            user.getEnfants().clear();
            user.getEnfants().addAll(userDetails.getEnfants());
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long idUser) {
        userRepository.deleteById(idUser);
    }

    public User addEnfantsToUser(Long userId, List<Enfant> enfants) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));

        for (Enfant enfant : enfants) {
            enfant.setParent(user);
        }

        user.getEnfants().addAll(enfants);
        return userRepository.save(user);
    }

    @Transactional
    public User assignUserToSociete(Long userId, Long societeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));

        Societe societe = societeRepository.findById(societeId)
                .orElseThrow(() -> new SocieteNotFoundException("Société non trouvée"));

        user.setSociete(societe);
        addMouvementHistorique(user, "Affectation à la société " + societe.getNom());

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
        List<UserCompagne> userCompagnes = userCompagneRepository.findBySupervisorIsNotNullOrProjectLeaderIsNotNull();
        List<UserCompagneDTO> result = new ArrayList<>();

        for (UserCompagne userCompagne : userCompagnes) {
            UserCompagneDTO dto = new UserCompagneDTO();
            dto.setUser(userCompagne.getUser());
            dto.setCompagneId(userCompagne.getCompagne().getId());
            dto.setFonctionId(userCompagne.getFonction().getId()); // Utilisez fonctionId au lieu de fonction
            dto.setCommentaire(userCompagne.getCommentaire());
            dto.setDateHeureFormation(userCompagne.getDateHeureFormation());
            dto.setDateAffectation(userCompagne.getDateAffectation());
            dto.setDateFinAffectation(userCompagne.getDateFinAffectation());

            if (userCompagne.getSupervisor() != null) {
                dto.setSupervisorId(userCompagne.getSupervisor().getIdUser());
            }
            if (userCompagne.getProjectLeader() != null) {
                dto.setProjectLeaderId(userCompagne.getProjectLeader().getIdUser());
            }

            result.add(dto);
        }

        return result;
    }

    @Transactional
    public ResponseEntity<?> changeUserPassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));

        // Vérifier si l'ancien mot de passe correspond
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ancien mot de passe incorrect");
        }

        // Mettre à jour le mot de passe
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok("Mot de passe mis à jour avec succès");
    }
}