package com.example.nexus.Entitie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.nexus.Entitie.inhertance.BaseEntity;
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
public class User extends BaseEntity {

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
    @OneToMany(mappedBy = "user")
    private List<EtatUser> etatsUser; 
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
    private String matricule;
    @Lob
    private byte[] photoCin;
    private String description;
    @NotNull
    private String password;
    @ManyToOne
    @JoinColumn(name = "typeContrat_id") 
    private TypeContrat typeContrat;
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
    private Societe societe;
    @ManyToOne
    @JoinColumn(name = "site_id")  // La clé étrangère qui fait référence à l'ID du site
    private Site site; // Le site auquel l'utilisateur est affecté
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserCompagne> userCompagnes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MouvementHistorique> MouvementHistoriques;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DemandeConge> demandesConge = new ArrayList<>();

  /* 
    @Enumerated(EnumType.STRING)
    private EtatUser etatActuel;
    */

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<AutorisationSortie> autorisationSorties = new ArrayList<>();
    ////// derniere update////
   
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanningUser> planningUsers;
    
}
