package com.example.nexus.Entitie;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCompagne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore

    private User user;

    @ManyToOne
    @JoinColumn(name = "compagne_id", nullable = false)
    @JsonIgnore
    private Compagne compagne;
    @ManyToOne
    @JoinColumn(name = "fonction_id")
    private Fonction fonction;
    private String commentaire; // Commentaire sur l'association
    private LocalDateTime dateHeureFormation; // Date et heure de formation
    private LocalDate dateAffectation; // Date d'affectation
    private LocalDate dateFinAffectation; // Date de fin d'affectation

    @ManyToOne
    @JoinColumn(name = "supervisor_id", nullable = true)
    private User supervisor; // Ajout du superviseur

    @ManyToOne
    @JoinColumn(name = "project_leader_id", nullable = true)
    private User projectLeader; // Ajout du chef de projet
}
