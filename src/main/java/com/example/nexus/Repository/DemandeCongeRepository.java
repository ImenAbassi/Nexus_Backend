package com.example.nexus.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nexus.Entitie.DemandeConge;
import com.example.nexus.Entitie.User;

public interface DemandeCongeRepository extends JpaRepository<DemandeConge, Long> {
       @SuppressWarnings("null")
    Optional<DemandeConge> findById(Long id);
    List<DemandeConge> findByUser(User user);
}
