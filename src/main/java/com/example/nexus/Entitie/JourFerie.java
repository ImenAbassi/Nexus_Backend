package com.example.nexus.Entitie;

import java.time.LocalDate;

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
public class JourFerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Pour les jours fériés fixes
    private String description;

    @ManyToOne  // Relation ManyToOne avec Pays
    @JoinColumn(name = "pays_id") // Nom de la colonne de jointure
    private Pays pays; // Relier chaque jour férié à un pays

    // Champs pour les jours fériés variables
    private Integer mois; // Mois pour un jour férié récurrent (1 = Janvier, etc.)
    private Integer semaineDansMois; // Ex: 1 pour "premier", 2 pour "deuxième", etc.

    @ManyToOne 
    @JoinColumn(name = "jourSemaine_id") 
    private JourSemaine jourSemaine; 

  

}
    
