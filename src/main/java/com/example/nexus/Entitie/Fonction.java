package com.example.nexus.Entitie;

import com.example.nexus.Entitie.inhertance.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fonction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // Exemple : "DIRECTION", "RH", "RECRUTEUR", etc.

    public Fonction() {
    }

    

    public Fonction(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }



    public Fonction(String nom) {
        this.nom = nom;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    

    
}
