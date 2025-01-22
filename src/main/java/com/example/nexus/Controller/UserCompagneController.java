package com.example.nexus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.nexus.Controller.api.UserCompagneApi;
import com.example.nexus.Dto.UserCompagneDTO;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Services.interfaces.UserCompagneService;
import com.example.nexus.mapper.ObjectMapper;

import java.util.List;

@RestController
public class UserCompagneController implements UserCompagneApi {

	@Autowired
	private UserCompagneService usercompagneService;

	@Override
	public ResponseEntity<UserCompagneDTO> add(UserCompagneDTO entity) {
		return new ResponseEntity<>(ObjectMapper.map(usercompagneService.add(ObjectMapper.map(entity, UserCompagne.class)), UserCompagneDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserCompagneDTO> update(Long id, UserCompagneDTO entity) {
		return new ResponseEntity<>(ObjectMapper.map(usercompagneService.update(ObjectMapper.map(entity,  UserCompagne.class)), UserCompagneDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		usercompagneService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserCompagneDTO> getById(Long id) {
		return new ResponseEntity<>(ObjectMapper.map(usercompagneService.findById(id), UserCompagneDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserCompagneDTO>> getAll() {
		return new ResponseEntity<>(ObjectMapper.mapAll(usercompagneService.findAll(), UserCompagneDTO.class), HttpStatus.OK);
	}

}