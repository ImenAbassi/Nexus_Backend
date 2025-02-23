package com.example.nexus.Entitie;

import com.example.nexus.Entitie.inhertance.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pointage extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate datePointage; // Date du pointage

    @OneToMany(mappedBy = "pointage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointageOperation> operations; // Liste des opérations de pointage (entrée/sortie)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Utilisateur associé au pointage

    @ManyToOne
    @JoinColumn(name = "planning_user_id")
    private PlanningUser planningUser; // Planning associé à l'utilisateur

    private Long heuresTravaillees; // Total des heures travaillées en minutes

    // Méthode pour calculer les heures travaillées
    public void calculerHeuresTravaillees() {
        if (operations == null || operations.isEmpty()) {
            System.out.println("Aucune opération de pointage trouvée.");
            this.heuresTravaillees = 0L;
            return;
        }

        long totalMinutes = 0;
        PointageOperation lastEntree = null;
        boolean erreurPointage = false;

        // Trier les opérations par heure croissante
        operations.sort(Comparator.comparing(PointageOperation::getHeure));

        for (PointageOperation operation : operations) {
            System.out.println("Opération : " + operation.getType() + " à " + operation.getHeure());

            if ("ENTREE".equals(operation.getType())) {
                if (lastEntree != null) {
                    // Il y a déjà une entrée en attente sans sortie -> Erreur de pointage
                    erreurPointage = true;
                } else {
                    lastEntree = operation; // Stocke l'entrée en attente d'une sortie
                }
            } else if ("SORTIE".equals(operation.getType())) {
                if (lastEntree == null) {
                    // Sortie sans entrée correspondante -> Erreur de pointage
                    System.out.println("Sortie sans entrée correspondante -> Erreur de pointage");
                    erreurPointage = true;
                } else {
                    totalMinutes += Duration.between(lastEntree.getHeure(), operation.getHeure()).toMinutes();
                    System.out.println("Paire entrée-sortie : " + totalMinutes + " minutes");
                    lastEntree = null; // Réinitialisation après une paire entrée-sortie
                }
            }
        }

        // Vérifier si une entrée est restée sans sortie
        if (lastEntree != null) {
            erreurPointage = true;
        }

        this.heuresTravaillees = totalMinutes;

        // Déclencher une alerte pour demander la correction du pointage si nécessaire
        if (erreurPointage) {
            System.out.println("⚠️ Erreur de pointage détectée pour l'utilisateur " + user.getNom() + ". Veuillez corriger les entrées/sorties incohérentes.");
        }
    }

}
