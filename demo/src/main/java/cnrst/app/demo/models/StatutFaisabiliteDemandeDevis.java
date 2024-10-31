package cnrst.app.demo.models;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "statutfaisabilitedemandedevis")
public class StatutFaisabiliteDemandeDevis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStatutFaisabiliteDemandeDevis")
    private Integer id;

    @Column(name = "dateDerniereModification")
    @NotNull(message = "La date de dernière modification ne peut pas être vide")
    private LocalDateTime dateDerniereModification;

    @Column(name = "abreviation")
    @Size(min = 2, max = 10, message = "L'abbréviation doit contenir entre 2 et 10 caractères")
    private String abreviation;

    @Column(name = "intitule")
    @NotNull(message = "L'intitulé ne peut pas être vide")
    private String intitule;

    @Column(name = "ordre")
    @NotNull(message = "L'ordre ne peut pas être vide")
    private int ordre;
    
    // Méthode appelée avant l'insertion de l'entité dans la base de données
    @PrePersist
    protected void onCreate() {
        // Vérifie si dateDerniereModification est null (pas encore initialisée)
        if (this.dateDerniereModification == null) {
            // Initialise dateDerniereModification à l'heure actuelle
            this.dateDerniereModification = LocalDateTime.now();
        }
    }

    // Méthode appelée avant la mise à jour de l'entité dans la base de données
    @PreUpdate
    protected void onUpdate() {
        // Met à jour dateDerniereModification à l'heure actuelle
        this.dateDerniereModification = LocalDateTime.now();
    }

}
