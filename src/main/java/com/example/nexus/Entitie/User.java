package com.example.nexus.Entitie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @NotNull

    private String prenom;
    @NotNull

    private String nom;
    private String nomJeuneFille;
    @ManyToOne
    @JoinColumn(name = "etatCivil_id") 
    private EtatCivil etatCivil;

    @ManyToOne
    @JoinColumn(name = "sexe_id") 
    private Sexe sexe;
/* 
    @Enumerated(EnumType.STRING)
    private EtatUser etatActuel;
    */
    @OneToMany(mappedBy = "user")
    private List<EtatUser> etatsUser; // Un utilisateur peut avoir plusieurs états


    @Column(columnDefinition = "DATE")
    private LocalDate dateNaissance;

    private String lieuNaissance;
    @NotNull
    @Column(unique = true)
    private String cin;

    @Column(columnDefinition = "DATE")
    private LocalDate delivreeLe;
    private String delivreeA;

    private String adresseMail;
    private String adresseMailPro;

    private String adresseCIN;
    private String adressePersonnelle2;
    private String adressePersonnelle3;

    private String codePostal;
    private String ville;

    private String telFixe;
    @NotNull
    private String telPortable1;
    private String telPortable2;

    private String nationalite;
    private String cnssCnrps;

    private String banque;
    private String agence;
    private String rib;

   
    @ManyToOne
    @JoinColumn(name = "typeContrat_id") 
    private TypeContrat typeContrat;

    private String matricule;

    @Lob
    private byte[] photoCin;

    private String description;
    @NotNull
    private String password;

    @Column(columnDefinition = "DATE")
    private LocalDate dateEntree;

    @Column(columnDefinition = "DATE")
    private LocalDate dateDebutContrat;

    @Column(columnDefinition = "DATE")
    private LocalDate dateFinContrat;

    @Lob
    private byte[] photoDiplome;
    @Lob
    private byte[] photoProfil;

    private double salaire;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Enfant> enfants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "societe_id")
    @JsonIgnore
    private Societe societe;
    @ManyToOne
    @JoinColumn(name = "site_id")  // La clé étrangère qui fait référence à l'ID du site
    private Site site; // Le site auquel l'utilisateur est affecté

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MouvementHistorique> MouvementHistoriques;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DemandeConge> demandesConge = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore

    private List<UserCompagne> userCompagnes;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<AutorisationSortie> autorisationSorties = new ArrayList<>();
    ////// derniere update////
   
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanningUser> planningUsers;
    





















    // Validation conditionnelle pour les dates de contrat
/* 
    @AssertTrue(message = "La date de début est obligatoire pour un contrat de type CDI, et les dates de début et de fin sont obligatoires pour un contrat de type SIVP.")
    public boolean isDateContratValid() {
        if (TypeContrat.CDI.equals(typeContrat)) {
            return dateDebutContrat != null; // Date de début obligatoire pour CDI
        } else if (TypeContrat.SIVP.equals(typeContrat)) {
            return dateDebutContrat != null && dateFinContrat != null; // Dates de début et de fin obligatoires pour
                                                                       // SIVP
        }
        return true; // Pas de contrainte pour d'autres types de contrat, le cas échéant
    }
    */
}
