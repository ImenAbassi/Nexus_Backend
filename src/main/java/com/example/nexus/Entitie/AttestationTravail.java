package com.example.nexus.Entitie;


import java.time.LocalDate;

import com.example.nexus.Entitie.inhertance.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class AttestationTravail extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "typeAttestation_id") 
    private TypeAttestation typeAttestation;
   
    @Column(columnDefinition = "DATE")

    private LocalDate dateSouhaitee; 
    @Column(columnDefinition = "DATE")

    private LocalDate dateCreation; 

    @Enumerated(EnumType.STRING)
    private EtatDemande etatDemande; 

    private String motif; 
    private String reference;

    @ManyToOne
    @JoinColumn(name = "utilisateur_idUser") 
    private User utilisateur;

}
