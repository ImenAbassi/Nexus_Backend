package com.example.nexus.Services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.User;
import com.example.nexus.Entitie.UserCompagne;
import com.example.nexus.Repository.UserCompagneRepository;
import com.example.nexus.Services.interfaces.UserCompagneService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserCompagneServiceImpl implements UserCompagneService {

	@Autowired
	private UserCompagneRepository usercompagneRepository;

	

	@Override
	public UserCompagne add(UserCompagne entity) {
		entity.setCreatedAt(new Date());
		return usercompagneRepository.save(entity);
	}

	@Override
	public UserCompagne update(UserCompagne entity) {
		entity.setUpdatedAt(new Date());
		return usercompagneRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		UserCompagne existingEntity = usercompagneRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Non trouv�e avec l'ID : " + id));
		existingEntity.setIsDeleted(Boolean.TRUE);
		existingEntity.setUpdatedAt(new Date());
		usercompagneRepository.save(existingEntity);
	}

	@Override
	public UserCompagne findById(Long id) {
		return usercompagneRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Non trouv�e avec l'ID : " + id));
	}

	@Override
	public List<UserCompagne> findAll() {
		try {
			return usercompagneRepository.findAll();
		} catch (Exception error) {
			System.err.println(error.getLocalizedMessage());
			return new ArrayList<>();
		}
	}
	
	@Override
	public List<UserCompagne> getAllBySupervisor(User supervisor) {
        return usercompagneRepository.findAllBySupervisor(supervisor);
    }

}