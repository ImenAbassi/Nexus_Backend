package com.example.nexus.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.nexus.Entitie.Fonction;
import com.example.nexus.Entitie.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCompagneDTO {
    private Long id;
    private Long userId;
    private User user;
    private Long compagneId;
    private Long supervisorId;
    private Long projectLeaderId;
    private Long fonctionId;
    private String commentaire;
    private LocalDate dateAffectation;
    private LocalDate dateFinAffectation;
    private LocalDateTime dateHeureFormation; // Date et heure de formation
    private Fonction fonction;

}
