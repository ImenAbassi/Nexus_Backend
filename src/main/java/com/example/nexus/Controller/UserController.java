package com.example.nexus.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.nexus.Dto.UserCompagneDTO;
import com.example.nexus.Entitie.Enfant;
import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

     @PostMapping("/create-from-candidat/{candidatId}")
    public ResponseEntity<Map<String, Object>> createUserFromCandidat(@PathVariable Long candidatId) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Appeler le service pour créer un User et un UserCompagne
            userService.createUserAndUserCompagneFromCandidat(candidatId);

            // Construire la réponse en cas de succès
            response.put("status", "success");
            response.put("message", "User and UserCompagne created successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Construire la réponse en cas d'erreur
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/create-and-assign")
    public ResponseEntity<?> createUserAndAssignToCompagne(@RequestBody UserCompagneDTO dto) {
        try {
            UserCompagne userCompagne = userService.createUserAndAssignToCompagne(
                    dto.getUser(),
                    dto.getCompagneId(),
                    dto.getFonctionId(),
                    dto.getCommentaire(),
                    dto.getDateAffectation(),
                    dto.getDateFinAffectation(),
                    dto.getDateHeureFormation()
            );
            return ResponseEntity.ok(userCompagne);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{userCompagneId}")
    public ResponseEntity<UserCompagne> getUserCompagneById(@PathVariable Long userCompagneId) {
        UserCompagne userCompagne = userService.findById(userCompagneId);
        if (userCompagne == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userCompagne);
    }

    @PutMapping("/update/{userCompagneId}")
    public ResponseEntity<UserCompagne> updateUserCompagne(@PathVariable Long userCompagneId,
                                                           @RequestBody UserCompagneDTO dto) {
        UserCompagne updatedUserCompagne = userService.updateUserCompagne(userCompagneId, dto);
      
        return ResponseEntity.ok(updatedUserCompagne);
    }

    @PostMapping("/assignToCompagne")
    public ResponseEntity<UserCompagne> assignUserToCompagne(@RequestBody UserCompagneDTO dto) {
        UserCompagne userCompagne = userService.assignUserToCompagne(
                dto.getUserId(),
                dto.getCompagneId(),
                dto.getFonctionId(),
                dto.getCommentaire(),
                dto.getDateFinAffectation()
        );
        return ResponseEntity.ok(userCompagne);
    }

    @PostMapping("/assignAgent")
    public ResponseEntity<UserCompagne> assignAgentToSupervisorAndProjectLeader(@RequestBody UserCompagneDTO dto) {
        UserCompagne userCompagne = userService.assignAgentToSupervisorAndProjectLeader(dto);
        return ResponseEntity.ok(userCompagne);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users-without-supervisor-or-project-leader")
    public List<UserCompagneDTO> getUsersWithoutSupervisorOrProjectLeader() {
        return userService.getUsersWithoutSupervisorOrProjectLeader();
    }

    @GetMapping("/users-with-supervisor-or-project-leader")
    public List<UserCompagneDTO> getUsersWithSupervisorOrProjectLeader() {
        return userService.getUsersWithSupervisorOrProjectLeader();
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable Long idUser) {
        return userService.getUserById(idUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable Long idUser, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(idUser, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser) {
        userService.deleteUser(idUser);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/enfants")
    public ResponseEntity<User> addEnfantsToUser(@PathVariable Long userId, @RequestBody List<Enfant> enfants) {
        User updatedUser = userService.addEnfantsToUser(userId, enfants);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/assign-societe/{societeId}")
    public ResponseEntity<User> assignUserToSociete(
            @PathVariable Long userId,
            @PathVariable Long societeId) {
        User updatedUser = userService.assignUserToSociete(userId, societeId);
        return ResponseEntity.ok(updatedUser);
    }
    @PostMapping("/{idUser}/change-password")
public ResponseEntity<?> changePassword(@PathVariable Long idUser, @RequestBody Map<String, String> passwordChangeRequest) {
    String oldPassword = passwordChangeRequest.get("oldPassword");
    String newPassword = passwordChangeRequest.get("newPassword");

    if (oldPassword == null || newPassword == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ancien et nouveau mot de passe sont requis");
    }

    return userService.changeUserPassword(idUser, oldPassword, newPassword);
}

 // Ajouter un User
 @PostMapping("add")
 public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user) {
     Map<String, Object> response = new HashMap<>();
     try {
         User savedUser = userService.saveUser(user);
         response.put("status", "success");
         response.put("data", savedUser);
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
     } catch (RuntimeException e) {
         response.put("status", "error");
         response.put("message", e.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
     }
 }

 // Modifier un User
 @PutMapping("update/{id}")
 public ResponseEntity<Map<String, Object>> updateUsers(@PathVariable Long id, @RequestBody User user) {
     Map<String, Object> response = new HashMap<>();
     try {
         user.setIdUser(id); // S'assurer que l'ID est correct
         User updatedUser = userService.saveUser(user);
         response.put("status", "success");
         response.put("data", updatedUser);
         return ResponseEntity.ok(response);
     } catch (RuntimeException e) {
         response.put("status", "error");
         response.put("message", e.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
     }
 }
}