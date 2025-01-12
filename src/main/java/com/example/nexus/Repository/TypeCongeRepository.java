package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.TypeConge;

@Repository
public interface TypeCongeRepository extends JpaRepository<TypeConge, Long> {
}
