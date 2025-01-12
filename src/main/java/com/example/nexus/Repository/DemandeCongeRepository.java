package com.example.nexus.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nexus.Entitie.DemandeConge;

public interface DemandeCongeRepository extends JpaRepository<DemandeConge, Long> {
       @SuppressWarnings("null")
    Optional<DemandeConge> findById(Long id);
}
