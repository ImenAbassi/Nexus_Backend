package com.example.nexus.Repository;

import com.example.nexus.Entitie.Compagne;
import com.example.nexus.Entitie.UserCompagne;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompagneRepository extends JpaRepository<Compagne, Long> {

}
