package com.example.nexus.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Fonction;

@Repository
public interface FonctionRepository extends JpaRepository<Fonction, Long> {
}
