package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Candidat;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {

}