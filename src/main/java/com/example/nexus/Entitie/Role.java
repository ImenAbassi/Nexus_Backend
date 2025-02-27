package com.example.nexus.Entitie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.nexus.Entitie.inhertance.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Role extends BaseEntity  implements Serializable {

	private static final long serialVersionUID = 2022120L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_privileges",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> privileges = new ArrayList<>();


	

	public Role(Long id, String name, String description, List<Privilege> privileges) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.privileges = privileges;
	}

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

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	

}