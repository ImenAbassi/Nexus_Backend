package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    
}
