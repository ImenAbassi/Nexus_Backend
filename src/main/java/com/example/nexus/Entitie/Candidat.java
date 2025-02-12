package com.example.nexus.Entitie;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.nexus.Entitie.inhertance.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Candidat extends BaseEntity  implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;

    private String nom;
    private LocalDateTime dateHeureFormation; // Add this field
    @ManyToOne
    @JoinColumn(name = "sexe_id") 
    private Sexe sexe;

    @NotNull
    @Column(unique = true)
    private String cin;

    private String adresseMail;

    @NotNull
    private String telPortable1;

    @ManyToOne
    private Compagne compagne;

}
