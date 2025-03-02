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

import com.example.nexus.Dto.UserCompagneDTO;
import com.example.nexus.Entitie.UserCompagne;

@CrossOrigin(origins = "*") 
@RequestMapping("/usercompagne")
public interface UserCompagneApi {

	@PostMapping("/add")
	ResponseEntity<UserCompagneDTO> add(@RequestBody UserCompagneDTO entity);

	@PutMapping("/update/{id}")
	ResponseEntity<UserCompagneDTO> update(@PathVariable Long id, @RequestBody UserCompagneDTO entity);

	@DeleteMapping("/delete/{id}")
	ResponseEntity<Void> delete(@PathVariable Long id);

	@GetMapping("/getById/{id}")
	ResponseEntity<UserCompagneDTO> getById(@PathVariable Long id);

	@GetMapping("/getAll")
	ResponseEntity<List<UserCompagneDTO>> getAll();

	  @GetMapping("/supervisor/{id}")
    public List<UserCompagne> getUserCompagnesBySupervisor(@PathVariable Long id);

}