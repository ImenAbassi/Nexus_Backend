package com.example.nexus.Dto;


import java.util.List;

import com.example.nexus.Entitie.Privilege;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RoleDTO  {

	private Long id;
	private String name;
	private String description;
	private List<Privilege> privileges;

	public RoleDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}