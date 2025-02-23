package com.example.nexus.Services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Privilege;
import com.example.nexus.Entitie.Role;
import com.example.nexus.Repository.PrivilegeRepository;
import com.example.nexus.Repository.RoleRepository;
import com.example.nexus.Services.interfaces.RoleService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
    private PrivilegeRepository privilegeRepository;

	private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user.getUsername();
	}

	@Override
	public Role add(Role entity) {
		entity.setCreatedAt(new Date());
		entity.setCreatedBy(this.getCurrentUser());
		return roleRepository.save(entity);
	}

	@Override
	public Role update(Role entity) {
		return roleRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		Role existingEntity = roleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Non trouv�e avec l'ID : " + id));
		existingEntity.setIsDeleted(Boolean.TRUE);
		existingEntity.setUpdatedAt(new Date());
		existingEntity.setUpdatedBy(this.getCurrentUser());
		roleRepository.save(existingEntity);
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Non trouv�e avec l'ID : " + id));
	}

	@Override
	public List<Role> findAll() {
		try {
			return roleRepository.findAll();
		} catch (Exception error) {
			System.err.println(error.getLocalizedMessage());
			return new ArrayList<>();
		}
	}

	@Override
	public List<Privilege> getRolePrivileges(Long roleId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
		return role.getPrivileges();
	}

	@Override
	public void addPrivilegeToRole(Long roleId, Long privilegeId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
		Privilege privilege = privilegeRepository.findById(privilegeId)
				.orElseThrow(() -> new RuntimeException("Privilège non trouvé"));
		role.getPrivileges().add(privilege);
		roleRepository.save(role);
	}

	@Override
	public void removePrivilegeFromRole(Long roleId, Long privilegeId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
		role.getPrivileges().removeIf(p -> p.getId().equals(privilegeId));
		roleRepository.save(role);
	}

}