package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Enfant;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Integer> {
}