package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.ActionType;

@Repository
public interface ActionTypeRepository extends JpaRepository<ActionType, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
    // Par exemple, rechercher un ActionType par libelle
    ActionType findByLibelle(String libelle);
}
