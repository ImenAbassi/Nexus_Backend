package com.example.nexus.Dto;

import java.time.LocalDate;

public class PointageDTO {


      private Long id;

    private LocalDate datePointage; // Date du pointage

    private UserDTO user; // Utilisateur associé au pointage


    private Long heuresTravaillees;



    

    public PointageDTO() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getDatePointage() {
        return datePointage;
    }


    public void setDatePointage(LocalDate datePointage) {
        this.datePointage = datePointage;
    }


    public UserDTO getUser() {
        return user;
    }


    public void setUser(UserDTO user) {
        this.user = user;
    }


    public Long getHeuresTravaillees() {
        return heuresTravaillees;
    }


    public void setHeuresTravaillees(Long heuresTravaillees) {
        this.heuresTravaillees = heuresTravaillees;
    } 

    
}
