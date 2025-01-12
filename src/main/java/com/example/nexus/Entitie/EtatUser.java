package com.example.nexus.Entitie;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class EtatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtatUser;

    @Column(nullable = false)
    private String statut; 

    private String description; 

    private LocalDate dateChangement; 

    // Relation ManyToOne avec User
    @ManyToOne
    private User user; // Un utilisateur peut avoir plusieurs états dans son historique
}
