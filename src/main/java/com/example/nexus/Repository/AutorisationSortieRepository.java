package com.example.nexus.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.AutorisationSortie;

@Repository
public interface AutorisationSortieRepository extends JpaRepository<AutorisationSortie, Long> {
    @SuppressWarnings("null")
    Optional<AutorisationSortie> findById(Long id);
}
