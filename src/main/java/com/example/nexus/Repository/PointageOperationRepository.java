package com.example.nexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Pointage;
import com.example.nexus.Entitie.PointageOperation;

@Repository
public interface PointageOperationRepository extends JpaRepository<PointageOperation, Long> {
        boolean existsByPointageAndType(Pointage pointage, String type);

}
