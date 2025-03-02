package com.example.nexus.Services.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Candidat;
import com.example.nexus.Repository.CandidatRepository;
import com.example.nexus.Services.EmailService;
import com.example.nexus.Services.interfaces.CandidatService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CandidatServiceImpl implements CandidatService {

	@Autowired
	private CandidatRepository candidatRepository;
	@Autowired
	private EmailService emailService;

	private String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user.getUsername();
	}

	@Override
	public Candidat add(Candidat entity) {
		entity.setCreatedAt(new Date());
		entity.setCreatedBy(this.getCurrentUser());
		Candidat savedCandidat = candidatRepository.save(entity);
		 // Vérifier si la date de formation est renseignée et envoyer un email
		 if (entity.getDateHeureFormation() != null && entity.getAdresseMail() != null) {
            String subject = "Confirmation de votre formation";
            String content = "Bonjour " + entity.getPrenom() + ",<br><br>"
                    + "Votre formation est prévue le : " + entity.getDateHeureFormation() + "<br><br>"
                    + "Merci de votre participation.<br>"
                    + "Cordialement, <br> L'équipe de formation.";

            emailService.sendEmail(entity.getAdresseMail(), subject, content);
        }
		return savedCandidat;
	}

	@Override
public Candidat update(Candidat entity) {
    // Récupérer l'ancien candidat depuis la base de données
    Optional<Candidat> optionalOldCandidat = candidatRepository.findById(entity.getId());

    if (optionalOldCandidat.isPresent()) {
        Candidat oldCandidat = optionalOldCandidat.get();
        
        // Sauvegarder les nouvelles modifications
        Candidat updatedCandidat = candidatRepository.save(entity);

        // Vérifier si la date de formation a été renseignée ou modifiée
        boolean isFormationDateUpdated = oldCandidat.getDateHeureFormation() == null 
                || !oldCandidat.getDateHeureFormation().equals(entity.getDateHeureFormation());

        if (entity.getAdresseMail() != null && isFormationDateUpdated) {
            String subject = "Confirmation de votre formation";
            String content = String.format(
                "Bonjour %s,<br><br>"
                + "Votre formation est prévue le : %s.<br><br>"
                + "Merci de votre participation.<br>"
                + "Cordialement,<br>L'équipe de formation.",
                entity.getPrenom(),
                entity.getDateHeureFormation()
            );

            emailService.sendEmail(entity.getAdresseMail(), subject, content);
        }

        return updatedCandidat;
    } else {
        throw new EntityNotFoundException("Candidat introuvable avec l'ID : " + entity.getId());
    }
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
