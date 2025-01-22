package com.example.nexus.Controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nexus.Dto.RoleDTO;

@CrossOrigin(origins = "*") 
@RequestMapping("/role")
public interface RoleApi {

	@PostMapping("/add")
	ResponseEntity<RoleDTO> add(@RequestBody RoleDTO entity);

	@PutMapping("/update/{id}")
	ResponseEntity<RoleDTO> update(@PathVariable Long id, @RequestBody RoleDTO entity);

	@DeleteMapping("/delete/{id}")
	ResponseEntity<Void> delete(@PathVariable Long id);

	@GetMapping("/getById/{id}")
	ResponseEntity<RoleDTO> getById(@PathVariable Long id);

	@GetMapping("/getAll")
	ResponseEntity<List<RoleDTO>> getAll();

}