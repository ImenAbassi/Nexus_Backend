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

import com.example.nexus.Dto.CandidatDTO;

@CrossOrigin(origins = "*") 
@RequestMapping("/candidats")
public interface CandidatApi {

	@PostMapping("/add")
	ResponseEntity<CandidatDTO> add(@RequestBody CandidatDTO entity);

	@PutMapping("/update/{id}")
	ResponseEntity<CandidatDTO> update(@PathVariable Long id, @RequestBody CandidatDTO entity);

	@DeleteMapping("/delete/{id}")
	ResponseEntity<Void> delete(@PathVariable Long id);

	@GetMapping("/getById/{id}")
	ResponseEntity<CandidatDTO> getById(@PathVariable Long id);

	@GetMapping("/getAll")
	ResponseEntity<List<CandidatDTO>> getAll();

}