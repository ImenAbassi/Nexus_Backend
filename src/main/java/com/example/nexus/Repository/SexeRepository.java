package com.example.nexus.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Sexe;

@Repository
public interface SexeRepository extends JpaRepository<Sexe, Long> {
}
