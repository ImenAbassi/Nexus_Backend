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
public class ValidationHistorique extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autorisation_sortie_id", nullable = false)
    private AutorisationSortie autorisationSortie;
    
    @ManyToOne
    @JoinColumn(name = "demande_conge_id", nullable = false)
    private DemandeConge demandeConge;
    
    private String role;
    
    @Enumerated(EnumType.STRING)
    private EtatDemande etat;

    @Column(columnDefinition = "DATE")
    private LocalDate dateValidation;
}
