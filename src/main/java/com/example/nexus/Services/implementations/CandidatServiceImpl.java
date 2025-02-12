package com.example.nexus.Services.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Candidat;
import com.example.nexus.Repository.CandidatRepository;
import com.example.nexus.Services.interfaces.CandidatService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CandidatServiceImpl implements CandidatService{

   
	@Autowired
	private CandidatRepository candidatRepository;

	private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user.getUsername();
	}

	@Override
	public Candidat add(Candidat entity) {
		entity.setCreatedAt(new Date());
		entity.setCreatedBy(this.getCurrentUser());
		return candidatRepository.save(entity);
	}

	@Override
	public Candidat update(Candidat entity) {
		return candidatRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		Candidat existingEntity = candidatRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Non trouv�e avec l'ID : " + id));
		existingEntity.setIsDeleted(Boolean.TRUE);
		existingEntity.setUpdatedAt(new Date());
		existingEntity.setUpdatedBy(this.getCurrentUser());
		candidatRepository.save(existingEntity);
	}

	@Override
	public Candidat findById(Long id) {
		return candidatRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Non trouv�e avec l'ID : " + id));
	}

	@Override
	public List<Candidat> findAll() {
		try {
			return candidatRepository.findAll();
		} catch (Exception error) {
			System.err.println(error.getLocalizedMessage());
			return new ArrayList<>();
		}
	}
    
}
