package com.example.nexus.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Site;
import com.example.nexus.Repository.SiteRepository;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    // Méthode pour récupérer tous les sites
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    // Méthode pour récupérer un site par son ID
    public Optional<Site> getSiteById(Long id) {
        return siteRepository.findById(id);
    }

    // Méthode pour ajouter un nouveau site
    public Site createSite(Site site) {
        return siteRepository.save(site);
    }

    // Méthode pour mettre à jour un site existant
    public Site updateSite(Long id, Site siteDetails) {
        Optional<Site> site = siteRepository.findById(id);
        if (site.isPresent()) {
            Site existingSite = site.get();
            existingSite.setNom(siteDetails.getNom());
            existingSite.setDescription(siteDetails.getDescription());
            existingSite.setAdresse(siteDetails.getAdresse());
            return siteRepository.save(existingSite);
        }
        return null; // Retourner null si le site n'existe pas
    }

    // Méthode pour supprimer un site
    public void deleteSite(Long id) {
        siteRepository.deleteById(id);
    }
}
