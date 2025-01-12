package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.TypeContrat;

@Repository
public interface TypeContratRepository extends JpaRepository<TypeContrat, Long> {
}
