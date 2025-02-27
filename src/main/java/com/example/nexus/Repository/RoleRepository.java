package com.example.nexus.Repository;

import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

}