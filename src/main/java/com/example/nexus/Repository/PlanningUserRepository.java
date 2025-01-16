package com.example.nexus.Repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nexus.Entitie.PlanningUser;

public interface PlanningUserRepository extends JpaRepository<PlanningUser, Long> {
    List<PlanningUser> findByUserIdUser(Long idUser);

    List<PlanningUser> findByHeureDebutBetween(LocalTime heureDebut, LocalTime heureFin);  // Recherche par plage horaire
}
