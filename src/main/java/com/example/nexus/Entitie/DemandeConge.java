package com.example.nexus.Entitie;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandeConge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "typeAttestation_id") 
    private TypeConge typeConge;

    @Column(columnDefinition = "DATE")
    private LocalDate dateCreation;

    @Column(columnDefinition = "DATE")
    private LocalDate dateDebut;

    @Column(columnDefinition = "DATE")
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private EtatDemande etatSuperviseur = EtatDemande.EN_ATTENTE;

    @Enumerated(EnumType.STRING)
    private EtatDemande etatChefProjet = EtatDemande.EN_ATTENTE;

    @Enumerated(EnumType.STRING)
    private EtatDemande etatRH = EtatDemande.EN_ATTENTE;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String raison;

    private long nbreJours;

    @OneToMany(mappedBy = "demandeConge", cascade = CascadeType.ALL)
    private List<ValidationHistorique> historique = new ArrayList<>();

    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculerNombreJours() {
        if (dateDebut != null && dateFin != null) {
            this.nbreJours = ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
        }
    }

    // Méthodes de validation
    public void validerParSuperviseur(EtatDemande etat) {
        this.etatSuperviseur = etat;
       /* addToHistorique("Superviseur", etat);*/
    }

    public void validerParChefProjet(EtatDemande etat) {
        this.etatChefProjet = etat;
        /*addToHistorique("Chef Projet", etat);*/
    }

    public void validerParRH(EtatDemande etat) {
        this.etatRH = etat;
       /*  addToHistorique("RH", etat);*/
    }

   /*  private void addToHistorique(String role, EtatDemande etat) {
        ValidationHistorique vh = new ValidationHistorique();
        vh.setDemandeConge(this);
        vh.setRole(role);
        vh.setEtat(etat);
        vh.setDateValidation(LocalDate.now());
        historique.add(vh);
    }*/
}
