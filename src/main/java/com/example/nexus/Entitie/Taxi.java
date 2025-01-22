package com.example.nexus.Entitie;

import java.time.LocalDate; // Importer LocalDate

import com.example.nexus.Entitie.inhertance.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "heure_depart_id", nullable = false)
    private HeureDepart heureDepart; // Référence à l'heure de départ

    @Enumerated(EnumType.STRING)
    private EtatDemande etatDemande;
    @Column(nullable = false)
    private LocalDate dateCreation; // Ajout du champ dateCreation
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}