package com.example.nexus.Entitie;

import java.time.LocalDate;

import com.example.nexus.Entitie.inhertance.BaseEntity;

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
public class MouvementHistorique extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateAction; // Date de l'action
    private String motif; // Motif de l'action
    @ManyToOne
    @JoinColumn(name = "etat_user_id", nullable = false)
    private EtatUser etat; // Association avec la nouvelle entité EtatUser
    @ManyToOne
    @JoinColumn(name = "action_type_id", nullable = false)
    private ActionType actionType; // Association avec la nouvelle entité ActionType
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
