package com.example.nexus.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Pointage;
import com.example.nexus.Entitie.User;

@Repository
public interface PointageRepository extends JpaRepository<Pointage, Long> {
        Optional<Pointage> findByUserAndDatePointage(User user, LocalDate date);
        List<Pointage> findByDatePointage(LocalDate date);
        Optional<Pointage> findByDatePointageAndUser(LocalDate datePointage, User user);

}
