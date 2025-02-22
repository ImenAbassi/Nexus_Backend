package com.example.nexus.Controller;


import com.example.nexus.Controller.api.PrivilegeApi;
import com.example.nexus.Entitie.Privilege;
import com.example.nexus.Services.PrivilegeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrivilegeController implements PrivilegeApi {

    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public ResponseEntity<Privilege> createPrivilege(Privilege privilege) {
        Privilege createdPrivilege = privilegeService.createPrivilege(privilege);
        return new ResponseEntity<>(createdPrivilege, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Privilege>> getAllPrivileges() {
        List<Privilege> privileges = privilegeService.getAllPrivileges();
        return new ResponseEntity<>(privileges, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Privilege> getPrivilegeById(Long id) {
        return privilegeService.getPrivilegeById(id)
                .map(privilege -> new ResponseEntity<>(privilege, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Privilege> updatePrivilege(Long id, Privilege updatedPrivilege) {
        try {
            Privilege updated = privilegeService.updatePrivilege(id, updatedPrivilege);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deletePrivilege(Long id) {
        privilegeService.deletePrivilege(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}