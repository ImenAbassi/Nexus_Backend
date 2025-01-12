package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nexus.Entitie.EtatCivil;

public interface EtatCivilRepository extends JpaRepository<EtatCivil, Long> {
}
