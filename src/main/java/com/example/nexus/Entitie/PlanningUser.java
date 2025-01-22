package com.example.nexus.Entitie;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
public class PlanningUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
@JoinColumn(name = "user_idUser", referencedColumnName = "idUser", nullable = false)
private User user;

    private LocalTime heureDebut; // Début de la plage horaire
    private LocalTime heureFin; // Fin de la plage horaire

    private boolean estPointé; // Indique si l'utilisateur a pointé dans cette plage horaire
    private LocalDateTime datePointage; // Date et heure réelle du pointage

    private String commentaire; // Optionnel : pour noter des observations
}
