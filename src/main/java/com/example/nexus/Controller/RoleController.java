package com.example.nexus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.nexus.Controller.api.RoleApi;
import com.example.nexus.Dto.RoleDTO;
import com.example.nexus.Entitie.Privilege;
import com.example.nexus.Entitie.Role;
import com.example.nexus.Services.interfaces.RoleService;
import com.example.nexus.mapper.ObjectMapper;

import java.util.List;
import java.util.Map;

@RestController
public class RoleController implements RoleApi {

	@Autowired
	private RoleService roleService;

	@Override
	public ResponseEntity<RoleDTO> add(RoleDTO entity) {
		return new ResponseEntity<>(ObjectMapper.map(roleService.add(ObjectMapper.map(entity, Role.class)), RoleDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RoleDTO> update(Long id, RoleDTO entity) {
		return new ResponseEntity<>(ObjectMapper.map(roleService.update(ObjectMapper.map(entity,  Role.class)), RoleDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		roleService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RoleDTO> getById(Long id) {
		return new ResponseEntity<>(ObjectMapper.map(roleService.findById(id), RoleDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RoleDTO>> getAll() {
		return new ResponseEntity<>(ObjectMapper.mapAll(roleService.findAll(), RoleDTO.class), HttpStatus.OK);
	}

    // Récupérer les privilèges d'un rôle
    @Override
    public ResponseEntity<List<Privilege>> getRolePrivileges(Long roleId) {
        List<Privilege> privileges = roleService.getRolePrivileges(roleId);
        return new ResponseEntity<>(privileges, HttpStatus.OK);
    }

    // Ajouter un privilège à un rôle
    @Override
    public ResponseEntity<Void> addPrivilegeToRole(Long roleId, Long privilegeId) {
        roleService.addPrivilegeToRole(roleId, privilegeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Supprimer un privilège d'un rôle
    @Override
    public ResponseEntity<Void> removePrivilegeFromRole(Long roleId, Long privilegeId) {
        roleService.removePrivilegeFromRole(roleId, privilegeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}