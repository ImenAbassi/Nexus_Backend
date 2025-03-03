package com.example.nexus.Entitie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Titre du post
    private String content; // Texte du post

    // Champs pour les médias (peuvent être nuls)
    private String mediaType; // "IMAGE", "VIDEO" ou null pour aucun média
    private String mediaUrl = ""; // Valeur par défaut

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reaction> reactions = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "post_reaction_counts", joinColumns = @JoinColumn(name = "post_id"))
    @MapKeyColumn(name = "reaction_type")
    @Column(name = "count")
    private Map<ReactionType, Long> reactionCounts = new HashMap<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters et Setters
}