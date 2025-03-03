package com.example.nexus.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Pointage;
import com.example.nexus.Entitie.User;

@Repository
public interface PointageRepository extends JpaRepository<Pointage, Long> {
        /*Optional<Pointage> findByUserAndDatePointage(User user, LocalDate date);
        List<Pointage> findByDatePointage(LocalDate date);
        Optional<Pointage> findByDatePointageAndUser(LocalDate datePointage, User user);*/
 // Find all Pointages by Supervisor
    @Query("SELECT p FROM Pointage p WHERE p.user.supervisor.idUser = :supervisorId")
    List<Pointage> findAllBySupervisor(@Param("supervisorId") Long supervisorId);

    // Find all Pointages by Project Leader
    @Query("SELECT p FROM Pointage p WHERE p.user.projectLeader.idUser = :projectLeaderId")
    List<Pointage> findAllByProjectLeader(@Param("projectLeaderId") Long projectLeaderId);
}
