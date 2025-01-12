package com.example.nexus.Repository;

import com.example.nexus.Entitie.MouvementHistorique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouvementHistoriqueRepository extends JpaRepository<MouvementHistorique, Long> {
List<MouvementHistorique> findByUser_IdUser(Integer idUser);
}
