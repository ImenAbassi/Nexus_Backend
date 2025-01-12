package com.example.nexus.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.Compagne;
import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;

@Repository
public interface UserCompagneRepository extends JpaRepository<UserCompagne, Long> {
    Optional<UserCompagne> findByUser_IdUserAndCompagne_Id(Long userId, Long compagneId);

    Optional<UserCompagne> findByUserAndCompagne(User user, Compagne compagne);

    List<UserCompagne> findAllByProjectLeader(User projectLeader);

    List<UserCompagne> findAllByCompagne(Compagne compagne);

    List<UserCompagne> findAllBySupervisor(User supervisor);
    List<UserCompagne> findAllByCompagne_Id(Long compagneId);
    List<UserCompagne> findAllByProjectLeader_IdUserAndCompagne_Id(Long projectLeaderId, Long compagneId);
    List<UserCompagne> findBySupervisorIsNullAndProjectLeaderIsNull();
    List<UserCompagne> findBySupervisorIsNotNullOrProjectLeaderIsNotNull();

}
