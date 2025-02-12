package com.example.nexus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Controller.api.CandidatApi;
import com.example.nexus.Dto.CandidatDTO;
import com.example.nexus.Entitie.Candidat;
import com.example.nexus.Services.interfaces.CandidatService;
import com.example.nexus.mapper.ObjectMapper;


@RestController
public class CandidatController implements CandidatApi {

    	@Autowired
	private CandidatService candidatService;

	@Override
	public ResponseEntity<CandidatDTO> add(CandidatDTO entity) {
		return new ResponseEntity<>(ObjectMapper.map(candidatService.add(ObjectMapper.map(entity, Candidat.class)), CandidatDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CandidatDTO> update(Long id, CandidatDTO entity) {
		return new ResponseEntity<>(ObjectMapper.map(candidatService.update(ObjectMapper.map(entity,  Candidat.class)), CandidatDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		candidatService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CandidatDTO> getById(Long id) {
		return new ResponseEntity<>(ObjectMapper.map(candidatService.findById(id), CandidatDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CandidatDTO>> getAll() {
		return new ResponseEntity<>(ObjectMapper.mapAll(candidatService.findAll(), CandidatDTO.class), HttpStatus.OK);
	}
    
}
