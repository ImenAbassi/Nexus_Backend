package com.example.nexus.Entitie;

import java.time.LocalDate;

import com.example.nexus.Entitie.inhertance.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Enfant extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnfant;

    private String prenom;
    @ManyToOne
    @JoinColumn(name = "sexe_id") 
    private Sexe sexe;
    @Column(columnDefinition = "DATE")
    private LocalDate dateNaissance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User parent;
}
