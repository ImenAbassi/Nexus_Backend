package com.example.nexus.Entitie;

import java.util.List;

import com.example.nexus.Entitie.inhertance.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compagne extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] logo;
    private String nom;
    private String client;
    private String description;
    @Column(name = "projetclient")
    private String Projetclient;
  
    @ManyToOne
    @JoinColumn(name = "cible_id") // Cette annotation crée une clé étrangère dans Compagne pour la table Cible
    private Cible cible;
   
    @ManyToOne
    @JoinColumn(name = "pays_id") // Cette annotation crée une clé étrangère dans Compagne pour la table Pays
    private Pays pays;
   
    @ManyToOne
    @JoinColumn(name = "langue_id") // Cette annotation crée une clé étrangère dans Compagne pour la table Langue
    private Langue langue;

      @ManyToOne
    @JoinColumn(name = "typologie_id")  // Cette annotation crée une clé étrangère dans Compagne pour la table Typologie
    private Typologie typologie;


    private double salaireBase;
    private double salaire2;

    private double salaire3;
   
    @ManyToOne
    @JoinColumn(name = "typologie_canal_id")  // Cette annotation crée une clé étrangère dans Compagne pour la table TypologieCanal
    private TypologieCanal typologieCanal;

    @ManyToMany
    @JoinTable(name = "compagne_planning", joinColumns = @JoinColumn(name = "compagne_id"), inverseJoinColumns = @JoinColumn(name = "planning_id"))
    private List<Planning> plannings;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "compagne_jour_ferie", joinColumns = @JoinColumn(name = "compagne_id"), inverseJoinColumns = @JoinColumn(name = "jour_ferie_id"))
    private List<JourFerie> joursFeries; // Relation avec JourFerie

    @OneToMany(mappedBy = "compagne")
    @JsonIgnore
    private List<UserCompagne> userCompagnes;


}
