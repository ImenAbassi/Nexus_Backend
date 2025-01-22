package com.example.nexus.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.nexus.Entitie.Compagne;
import com.example.nexus.Entitie.Fonction;
import com.example.nexus.Entitie.Role;
import com.example.nexus.Entitie.User;

public class UserCompagneDTO {

	private Long id;
	private Long userId;
	private User user;
	private Compagne compagne;
	private Fonction fonction;
	private String commentaire;
	private Long compagneId;
	private Long fonctionId;
	private LocalDateTime dateHeureFormation;
	private LocalDate dateAffectation;
	private LocalDate dateFinAffectation;
	private User supervisor;
	private User projectLeader;
	private Role role;
	private Long supervisorId;
	private Long projectLeaderId;

	public UserCompagneDTO () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Compagne getCompagne() {
		return compagne;
	}

	public void setCompagne(Compagne compagne) {
		this.compagne = compagne;
	}

	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public LocalDateTime getDateHeureFormation() {
		return dateHeureFormation;
	}

	public void setDateHeureFormation(LocalDateTime dateHeureFormation) {
		this.dateHeureFormation = dateHeureFormation;
	}

	public LocalDate getDateAffectation() {
		return dateAffectation;
	}

	public void setDateAffectation(LocalDate dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public LocalDate getDateFinAffectation() {
		return dateFinAffectation;
	}

	public void setDateFinAffectation(LocalDate dateFinAffectation) {
		this.dateFinAffectation = dateFinAffectation;
	}

	public User getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}

	public User getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(User projectLeader) {
		this.projectLeader = projectLeader;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getCompagneId() {
		return compagneId;
	}

	public void setCompagneId(Long compagneId) {
		this.compagneId = compagneId;
	}

	public Long getFonctionId() {
		return fonctionId;
	}

	public void setFonctionId(Long fonctionId) {
		this.fonctionId = fonctionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public Long getProjectLeaderId() {
		return projectLeaderId;
	}

	public void setProjectLeaderId(Long projectLeaderId) {
		this.projectLeaderId = projectLeaderId;
	}

	

}