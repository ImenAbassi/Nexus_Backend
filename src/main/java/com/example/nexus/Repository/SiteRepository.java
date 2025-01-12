package com.example.nexus.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nexus.Entitie.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {
    // Pas besoin d'ajouter de méthodes supplémentaires, JpaRepository fournit déjà les opérations de base.
}
