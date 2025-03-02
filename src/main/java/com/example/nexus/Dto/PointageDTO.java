package com.example.nexus.Dto;

import java.time.LocalDate;
import java.util.List;

import com.example.nexus.Entitie.EtatDemande;




public class PointageDTO {


      private Long id;



    private LocalDate datePointage;

    private UserCompagneDTO user;

    private List<PointageOperationDTO> listOperation;

    private int retard; // En heures

    private double totalHeure;

    private EtatDemande etatDemande;

    

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



    public UserCompagneDTO getUser() {
        return user;
    }



    public void setUser(UserCompagneDTO user) {
        this.user = user;
    }



    public List<PointageOperationDTO> getListOperation() {
        return listOperation;
    }



    public void setListOperation(List<PointageOperationDTO> listOperation) {
        this.listOperation = listOperation;
    }



    public int getRetard() {
        return retard;
    }



    public void setRetard(int retard) {
        this.retard = retard;
    }



    public double getTotalHeure() {
        return totalHeure;
    }



    public void setTotalHeure(double totalHeure) {
        this.totalHeure = totalHeure;
    }



    public EtatDemande getEtatDemande() {
        return etatDemande;
    }



    public void setEtatDemande(EtatDemande etatDemande) {
        this.etatDemande = etatDemande;
    }


    


    

    
}
