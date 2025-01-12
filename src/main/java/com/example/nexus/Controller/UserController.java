package com.example.nexus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

  
    @PostMapping("/create-and-assign")
    public ResponseEntity<UserCompagne> createUserAndAssignToCompagne(@RequestBody UserCompagneDTO dto) {
        UserCompagne userCompagne = userService.createUserAndAssignToCompagne(dto.getUser(), dto.getCompagneId(),
                dto.getFonction(), dto.getCommentaire(), dto.getDateAffectation(), dto.getDateFinAffectation(),dto.getDateHeureFormation());
        return ResponseEntity.ok(userCompagne);
    }
 // Contrôleur pour récupérer un UserCompagne par son ID
@GetMapping("/{userCompagneId}")
public ResponseEntity<UserCompagne> getUserCompagneById(@PathVariable Long userCompagneId) {
    UserCompagne userCompagne = userService.findById(userCompagneId);
    if (userCompagne == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(userCompagne);
}

@PutMapping("/update/{userCompagneId}")
public ResponseEntity<UserCompagne> updateUserCompagne(@PathVariable Long userCompagneId, @RequestBody UserCompagneDTO dto) {
    // Appel au service pour mettre à jour l'enregistrement
    UserCompagne updatedUserCompagne = userService.updateUserCompagne(userCompagneId, dto);

    if (updatedUserCompagne == null) {
        return ResponseEntity.notFound().build();  // Retourne 404 si l'élément n'existe pas
    }

    return ResponseEntity.ok(updatedUserCompagne);  // Retourne l'objet mis à jour
}

    @PostMapping("/assignToCompagne")
    public ResponseEntity<UserCompagne> assignUserToCompagne(@RequestBody UserCompagneDTO dto) {
        UserCompagne userCompagne = userService.assignUserToCompagne(dto.getUserId(), dto.getCompagneId(),
                dto.getFonction(), dto.getCommentaire(), dto.getDateFinAffectation());
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
        // Appeler le service pour récupérer les données enrichies
        List<UserCompagneDTO> userCompagnes = userService.getUsersWithoutSupervisorOrProjectLeader();
        return userCompagnes; // Renvoyer la liste des DTOs
    }
    @GetMapping("/users-with-supervisor-or-project-leader")
public List<UserCompagneDTO> getUsersWithSupervisorOrProjectLeader() {
    // Appeler le service pour récupérer les données enrichies
    List<UserCompagneDTO> userCompagnes = userService.getUsersWithSupervisorOrProjectLeader();
    return userCompagnes; // Renvoyer la liste des DTOs
}
/* 
    @GetMapping("/projectLeader/{projectLeaderId}/supervisors")
    public ResponseEntity<List<User>> getSupervisorsForProjectLeader(@PathVariable Long projectLeaderId) {
        List<User> supervisors = userService.getSupervisorsForProjectLeader(projectLeaderId);
        return ResponseEntity.ok(supervisors);
    }

    @GetMapping("/compagne/{compagneId}/projectLeaders")
    public ResponseEntity<List<User>> getProjectLeadersForCompagne(@PathVariable Long compagneId) {
        List<User> projectLeaders = userService.getProjectLeadersForCompagne(compagneId);
        return ResponseEntity.ok(projectLeaders);
    }

    @GetMapping("/supervisor/{supervisorId}/agents")
    public ResponseEntity<List<User>> getAgentsForSupervisor(@PathVariable Long supervisorId) {
        List<User> agents = userService.getAgentsForSupervisor(supervisorId);
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/compagne/{compagneId}/agents")
    public ResponseEntity<List<User>> getAgentsByCompagne(@PathVariable Long compagneId) {
        List<User> agents = userService.getAgentsByCompagne(compagneId);
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/compagne/{compagneId}/supervisors")
    public ResponseEntity<List<User>> getSupervisorsByCompagne(@PathVariable Long compagneId) {
        List<User> supervisors = userService.getSupervisorsByCompagne(compagneId);
        return ResponseEntity.ok(supervisors);
    }

    @GetMapping("/projectLeader/{projectLeaderId}/compagne/{compagneId}/agents")
    public ResponseEntity<List<User>> getAgentsByProjectLeaderAndCompagne(@PathVariable Long projectLeaderId,
            @PathVariable Long compagneId) {
        List<User> agents = userService.getAgentsByProjectLeaderAndCompagne(projectLeaderId, compagneId);
        return ResponseEntity.ok(agents);
    }
*/
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

   /*  @PutMapping("/{idUser}/etat")
    public ResponseEntity<Void> updateUserEtat(@PathVariable Long idUser,
            @RequestParam EtatUser nouvelEtat,
            @RequestParam String motif) {
        userService.updateUserEtat(idUser, nouvelEtat, motif);
        return ResponseEntity.ok().build();
    }
    */

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
}
