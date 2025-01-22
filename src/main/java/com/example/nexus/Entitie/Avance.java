package com.example.nexus.Entitie;

import java.time.LocalDate;

import com.example.nexus.Entitie.inhertance.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avance extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCreation; // Date de passage à l'état ACCEPTEE
    private LocalDate dateDemande;  // Date système de création de la demande d'avance

    @Column(name = "montant", precision = 10, scale = 3)
    private BigDecimal montant;
    
    @Enumerated(EnumType.STRING)
    private EtatDemande etatDemande; // État de la demande

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
        @JsonIgnore
    private User user; // Relation avec User
}
