package com.example.nexus.Dto;

import java.time.LocalDateTime;

import com.example.nexus.Entitie.Sexe;

public class CandidatDTO {

    private Long id;

    private String prenom;

    private String nom;

    private Sexe sexe;

    private String cin;

    private String adresseMail;

    private String telPortable1;
    private LocalDateTime dateHeureFormation; // Add this field

    public LocalDateTime getDateHeureFormation() {
        return dateHeureFormation;
    }

    public void setDateHeureFormation(LocalDateTime dateHeureFormation) {
        this.dateHeureFormation = dateHeureFormation;
    }

    private CompagneDTO compagne;

    public CandidatDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getTelPortable1() {
        return telPortable1;
    }

    public void setTelPortable1(String telPortable1) {
        this.telPortable1 = telPortable1;
    }

    public CompagneDTO getCompagne() {
        return compagne;
    }

    public void setCompagne(CompagneDTO compagne) {
        this.compagne = compagne;
    }

}