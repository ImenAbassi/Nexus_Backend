package com.example.nexus.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.ActionType;
import com.example.nexus.Repository.ActionTypeRepository;

@Service
public class ActionTypeService {

    @Autowired
    private ActionTypeRepository actionTypeRepository;

    public List<ActionType> getAllActionTypes() {
        return actionTypeRepository.findAll();
    }

    public Optional<ActionType> getActionTypeById(Long id) {
        return actionTypeRepository.findById(id);
    }

    public ActionType createActionType(ActionType actionType) {
        return actionTypeRepository.save(actionType);
    }

    public ActionType updateActionType(Long id, ActionType actionTypeDetails) {
        if (actionTypeRepository.existsById(id)) {
            actionTypeDetails.setId(id);
            return actionTypeRepository.save(actionTypeDetails);
        }
        return null;
    }

    public void deleteActionType(Long id) {
        actionTypeRepository.deleteById(id);
    }
}
