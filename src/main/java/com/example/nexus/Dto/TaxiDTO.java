package com.example.nexus.Dto;

import java.time.LocalDate;

import com.example.nexus.Entitie.EtatDemande;

public class TaxiDTO {
        private Long id;

    private String localisationArrivee; // Localisation d'arrivée

    private String heureDepart; // Heure de départ


    private EtatDemande etatDemande;

    private LocalDate dateCreation; // Date de création de la demande

    private UserDTO user; // Utilisateur qui a fait la demande

    public TaxiDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLocalisationArrivee() {
        return localisationArrivee;
    }

    public void setLocalisationArrivee(String localisationArrivee) {
        this.localisationArrivee = localisationArrivee;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public EtatDemande getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(EtatDemande etatDemande) {
        this.etatDemande = etatDemande;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }


    
}
