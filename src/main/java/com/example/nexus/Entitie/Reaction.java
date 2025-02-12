package com.example.nexus.Entitie;

import java.time.LocalDateTime;

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
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReactionType type; // Type de la réaction (LIKE, LOVE, HAHA, etc.)

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String reactedBy; // Nom ou ID de l'utilisateur ayant réagi

    private LocalDateTime reactedAt;

    // Getters et Setters
}

