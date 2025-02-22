package com.example.nexus.Controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nexus.Entitie.Privilege;

@CrossOrigin(origins = "*") 
@RequestMapping("/privileges")
public interface PrivilegeApi {

    @PostMapping
    ResponseEntity<Privilege> createPrivilege(@RequestBody Privilege privilege);

    @GetMapping
    ResponseEntity<List<Privilege>> getAllPrivileges();

    @GetMapping("/{id}")
    ResponseEntity<Privilege> getPrivilegeById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<Privilege> updatePrivilege(@PathVariable Long id, @RequestBody Privilege updatedPrivilege);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePrivilege(@PathVariable Long id);
}