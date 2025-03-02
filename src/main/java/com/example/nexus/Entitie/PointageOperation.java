package com.example.nexus.Entitie;

import java.time.LocalDateTime;

import com.example.nexus.Entitie.inhertance.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PointageOperation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pointage_id", nullable = false)
    private Pointage pointage;

    @ManyToOne
    @JoinColumn(name = "type_pointage_id", nullable = false)
    private TypePointage typePointage;

    @Column(name = "total_heure", nullable = false)
    private double totalHeure;

}
