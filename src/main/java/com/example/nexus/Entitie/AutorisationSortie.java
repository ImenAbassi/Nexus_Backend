package com.example.nexus.Entitie;
import java.time.LocalDate;

import com.example.nexus.Entitie.inhertance.BaseEntity;

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
public class AutorisationSortie extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "DATE")
    private LocalDate dateCreation;

    @Column(columnDefinition = "DATE")
    private LocalDate dateDebut;

    @Column(columnDefinition = "DATE")
    private LocalDate dateFin;

    private String heureDebut;
    private String heureFin;

    private int nbreHeure;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User utilisateur;
    
    @Enumerated(EnumType.STRING)
    private EtatDemande etatSuperviseur = EtatDemande.EN_ATTENTE;
    
    @Enumerated(EnumType.STRING)
    private EtatDemande etatChefProjet = EtatDemande.EN_ATTENTE;
    
    @Enumerated(EnumType.STRING)
    private EtatDemande etatRH = EtatDemande.EN_ATTENTE;


    private String raison;

 /*   @OneToMany(mappedBy = "autorisationSortie", cascade = CascadeType.ALL)
    private List<ValidationHistorique> historique = new ArrayList<>();
    
    // Méthodes de validation
    public void validerParSuperviseur(EtatDemande etat) {
        this.etatSuperviseur = etat;
        addToHistorique("Superviseur", etat);
    }

    public void validerParChefProjet(EtatDemande etat) {
        this.etatChefProjet = etat;
        addToHistorique("Chef Projet", etat);
    }

    public void validerParRH(EtatDemande etat) {
        this.etatRH = etat;
        addToHistorique("RH", etat);
    }

    private void addToHistorique(String role, EtatDemande etat) {
        ValidationHistorique vh = new ValidationHistorique();
        vh.setAutorisationSortie(this);
        vh.setRole(role);
        vh.setEtat(etat);
        vh.setDateValidation(LocalDate.now());
        historique.add(vh);
    }*/ 
}
