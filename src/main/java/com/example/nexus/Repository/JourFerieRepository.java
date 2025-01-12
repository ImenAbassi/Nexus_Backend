package com.example.nexus.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nexus.Entitie.JourFerie;
import com.example.nexus.Entitie.Pays;

import java.util.List;

public interface JourFerieRepository extends JpaRepository<JourFerie, Long> {
    List<JourFerie> findByPays(Pays pays);
}