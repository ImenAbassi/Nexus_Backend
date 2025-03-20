package com.example.nexus.Entitie;

import java.time.LocalDate;

import com.example.nexus.Entitie.inhertance.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Taxi extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String localisationArrivee; // Localisation d'arrivée

    private String localisationMap; // Localisation d'arrivée

    @Column(name = "heure_depart")
    private String heureDepart; // Heure de départ


    @Enumerated(EnumType.STRING)
    private EtatDemande etatDemande;

    @Column(nullable = false)
    private LocalDate dateCreation; // Date de création de la demande

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Utilisateur qui a fait la demande
}