package com.example.nexus.Services;

import com.example.nexus.Entitie.Privilege;
import com.example.nexus.Repository.PrivilegeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    // Create a new privilege
    public Privilege createPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    // Get all privileges
    public List<Privilege> getAllPrivileges() {
        return privilegeRepository.findAll();
    }

    // Get a privilege by ID
    public Optional<Privilege> getPrivilegeById(Long id) {
        return privilegeRepository.findById(id);
    }

    // Update an existing privilege
    public Privilege updatePrivilege(Long id, Privilege updatedPrivilege) {
        return privilegeRepository.findById(id)
                .map(privilege -> {
                    privilege.setName(updatedPrivilege.getName());
                    return privilegeRepository.save(privilege);
                })
                .orElseThrow(() -> new RuntimeException("Privilege not found with ID: " + id));
    }

    // Delete a privilege by ID
    public void deletePrivilege(Long id) {
        privilegeRepository.deleteById(id);
    }
}