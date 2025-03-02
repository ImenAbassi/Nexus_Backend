package com.example.nexus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Pointage;
import com.example.nexus.Entitie.PointageOperation;

@Repository
public interface PointageOperationRepository extends JpaRepository<PointageOperation, Long> {
        boolean existsByPointageAndType(Pointage pointage, String type);
        List<PointageOperation> findByPointageId(Long pointageId);
}
