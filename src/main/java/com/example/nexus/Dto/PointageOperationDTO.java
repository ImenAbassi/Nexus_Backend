package com.example.nexus.Dto;

import com.example.nexus.Entitie.TypePointage;

public class PointageOperationDTO {

    private Long id;

    private TypePointage typePointage;

    private double totalHeure;

    public PointageOperationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    public double getTotalHeure() {
        return totalHeure;
    }

    public void setTotalHeure(double totalHeure) {
        this.totalHeure = totalHeure;
    }

    public TypePointage getTypePointage() {
        return typePointage;
    }

    public void setTypePointage(TypePointage typePointage) {
        this.typePointage = typePointage;
    }

   

   


}