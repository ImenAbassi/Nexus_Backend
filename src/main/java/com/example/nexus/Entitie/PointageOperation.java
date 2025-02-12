package com.example.nexus.Entitie;

import java.time.LocalDateTime;

import com.example.nexus.Entitie.inhertance.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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

    private String compagne;

    @Column(nullable = false)
    private LocalDateTime heure;

    @Column(nullable = false)
    private String type;

     @PrePersist
    @PreUpdate
    public void updatePointageHeuresTravaillees() {
        if (pointage != null) {
            pointage.calculerHeuresTravaillees();
        }
    }
}
