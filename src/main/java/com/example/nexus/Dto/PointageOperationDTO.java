package com.example.nexus.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class PointageOperationDTO {

            private Long id;

    private String compagne;

    private LocalDateTime heure;

    private String type;

    public PointageOperationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompagne() {
        return compagne;
    }

    public void setCompagne(String compagne) {
        this.compagne = compagne;
    }

    public LocalDateTime getHeure() {
        return heure;
    }

    public void setHeure(LocalDateTime heure) {
        this.heure = heure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}