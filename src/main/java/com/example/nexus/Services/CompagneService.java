package com.example.nexus.Services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.Compagne;
import com.example.nexus.Entitie.JourFerie;
import com.example.nexus.Entitie.Planning;
import com.example.nexus.Repository.CompagneRepository;
import com.example.nexus.Repository.JourFerieRepository;
import com.example.nexus.Repository.PlanningRepository;

@Service
public class CompagneService {

    @Autowired
    private CompagneRepository compagneRepository;
    @Autowired
    private JourFerieRepository jourFerieRepository;
    @Autowired

    private PlanningRepository planningRepository;

    public Compagne createCompagne(Compagne compagne) {
        if (compagne.getPlannings() != null) {
            List<Long> planningIds = compagne.getPlannings().stream().map(Planning::getId).toList();
            List<Planning> plannings = planningRepository.findAllById(planningIds);
            compagne.setPlannings(plannings);
        }
         // Ici, vous pouvez traiter le logo comme un byte[]
         byte[] logo = compagne.getLogo(); // Le logo est déjà sous forme de byte[]

        return compagneRepository.save(compagne);
    }

    public List<Compagne> getAllCompagnes() {
        return compagneRepository.findAll();
    }

    public Optional<Compagne> getCompagneById(Long id) {
        return compagneRepository.findById(id);
    }

  /*   public Compagne updateCompagne(Long id, Compagne compagneDetails) {
        // Récupérer l'entité existante depuis le repository
        Compagne compagne = compagneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compagne non trouvée"));

        // Mettre à jour chaque champ uniquement si une valeur est fournie
        if (compagneDetails.getNom() != null) {
            compagne.setNom(compagneDetails.getNom());
        }
        if (compagneDetails.getClient() != null) {
            compagne.setClient(compagneDetails.getClient());
        }
        if (compagneDetails.getProjetclient() != null) {
            compagne.setProjetclient(compagneDetails.getProjetclient());
        }
        if (compagneDetails.getCible() != null) {
            compagne.setCible(compagneDetails.getCible());
        }
        if (compagneDetails.getPays() != null) {
            compagne.setPays(compagneDetails.getPays());
        }
        if (compagneDetails.getLangue() != null) {
            compagne.setLangue(compagneDetails.getLangue());
        }
        if (compagneDetails.getTypologie() != null) {
            compagne.setTypologie(compagneDetails.getTypologie());
        }
        if (compagneDetails.getSalaireBase() != 0) { // Assurez-vous que le salaire est mis à jour uniquement si
                                                     // différent de 0
            compagne.setSalaireBase(compagneDetails.getSalaireBase());
        }
        if (compagneDetails.getSalaire2() != 0) {
            compagne.setSalaire2(compagneDetails.getSalaire2());
        }
        if (compagneDetails.getSalaire3() != 0) {
            compagne.setSalaire3(compagneDetails.getSalaire3());
        }
        if (compagneDetails.getTypologiecanal() != null) {
            compagne.setTypologiecanal(compagneDetails.getTypologiecanal());
        }

        // Sauvegarder les modifications
        return compagneRepository.save(compagne);
    }
*/
    public void deleteCompagne(Long id) {
        compagneRepository.deleteById(id);

    }

    public Compagne addPlanningsToCompagne(Long compagneId, Long planningId) {
        // Rechercher la campagne par ID
        Compagne compagne = compagneRepository.findById(compagneId)
                .orElseThrow(() -> new RuntimeException("Compagne non trouvée"));

        // Rechercher le planning par ID
        Planning planning = planningRepository.findById(planningId)
                .orElseThrow(() -> new RuntimeException("Planning non trouvé"));

        // Ajouter le planning à la liste des plannings de la campagne
        if (!compagne.getPlannings().contains(planning)) {
            compagne.getPlannings().add(planning);
        }

        // Enregistrer la campagne mise à jour dans le repository
        return compagneRepository.save(compagne);
    }

    // Calcul des jours fériés variables (ex: premier dimanche de mois donné)
    public LocalDate calculerJourFerieRecurrent(int annee, Integer mois, Integer semaineDansMois,
            DayOfWeek jourSemaine) {
        if (mois == null || semaineDansMois == null || jourSemaine == null) {
            return null;
        }
        return LocalDate.of(annee, mois, 1).with(TemporalAdjusters.dayOfWeekInMonth(semaineDansMois, jourSemaine));
    }

    // Récupère tous les jours fériés (fixes et récurrents) pour une année et un
    // pays donnés
   /*  public List<LocalDate> getJoursFeriesForCompagne(Compagne compagne, int annee) {
        List<JourFerie> joursFeries = jourFerieRepository.findByPays(compagne.getPays());

        return joursFeries.stream().map(jourFerie -> {
            if (jourFerie.getDate() != null) {
                return jourFerie.getDate().withYear(annee); // Jours fixes
            } else {
                // Jours récurrents
                return calculerJourFerieRecurrent(annee, jourFerie.getMois(), jourFerie.getSemaineDansMois(),
                        jourFerie.getJourSemaine());
            }
        }).collect(Collectors.toList());
    }
*/
    // Ajouter un jour férié à une campagne
    public Compagne addJourFerieToCompagne(Long compagneId, Long jourFerieId) {
        Compagne compagne = compagneRepository.findById(compagneId)
                .orElseThrow(() -> new RuntimeException("Compagne non trouvée"));
        JourFerie jourFerie = jourFerieRepository.findById(jourFerieId)
                .orElseThrow(() -> new RuntimeException("Jour férié non trouvé"));

        compagne.getJoursFeries().add(jourFerie);
        return compagneRepository.save(compagne);
    }

    // Dissocier un jour férié d'une campagne
    public Compagne removeJourFerieFromCompagne(Long compagneId, Long jourFerieId) {
        Compagne compagne = compagneRepository.findById(compagneId)
                .orElseThrow(() -> new RuntimeException("Compagne non trouvée"));
        JourFerie jourFerie = jourFerieRepository.findById(jourFerieId)
                .orElseThrow(() -> new RuntimeException("Jour férié non trouvé"));

        compagne.getJoursFeries().remove(jourFerie);
        return compagneRepository.save(compagne);
    }
}
