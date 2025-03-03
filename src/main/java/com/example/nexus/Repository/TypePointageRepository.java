package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.TypePointage;

@Repository
public interface TypePointageRepository extends JpaRepository<TypePointage, Long> {
}
