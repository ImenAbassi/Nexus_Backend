package com.example.nexus.Controller;

import java.util.List;
import java.util.Optional;

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

import com.example.nexus.Entitie.ActionType;
import com.example.nexus.Services.ActionTypeService;

@RestController
@RequestMapping("/actionTypes")
public class ActionTypeController {

    @Autowired
    private ActionTypeService actionTypeService;

    // Récupérer tous les types d'action
    @GetMapping
    public List<ActionType> getAllActionTypes() {
        return actionTypeService.getAllActionTypes();
    }

    // Récupérer un type d'action par son ID
    @GetMapping("/{id}")
    public ResponseEntity<ActionType> getActionTypeById(@PathVariable Long id) {
        Optional<ActionType> actionType = actionTypeService.getActionTypeById(id);
        return actionType.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Créer un nouveau type d'action
    @PostMapping
    public ResponseEntity<ActionType> createActionType(@RequestBody ActionType actionType) {
        ActionType newActionType = actionTypeService.createActionType(actionType);
        return ResponseEntity.status(HttpStatus.CREATED).body(newActionType);
    }

    // Mettre à jour un type d'action existant
    @PutMapping("/{id}")
    public ResponseEntity<ActionType> updateActionType(@PathVariable Long id, @RequestBody ActionType actionTypeDetails) {
        ActionType updatedActionType = actionTypeService.updateActionType(id, actionTypeDetails);
        return updatedActionType != null ? ResponseEntity.ok(updatedActionType) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Supprimer un type d'action
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActionType(@PathVariable Long id) {
        actionTypeService.deleteActionType(id);
        return ResponseEntity.noContent().build();
    }
}
