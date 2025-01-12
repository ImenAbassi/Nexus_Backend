package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.TypeAttestation;

@Repository
public interface TypeAttestationRepository extends JpaRepository<TypeAttestation, Long> {
}
