package cnrst.app.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "laboratoire")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Laboratoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLaboratoire")
    private Long idLaboratoire;

    @Column(name = "dateDerniereModification")
    private LocalDateTime dateDerniereModification;

    @Column(name = "abreviation", length = 255)
    private String abreviation;

    @Column(name = "intitule", length = 255)
    private String intitule;

    @Column(name = "ordre", nullable = false)
    private int ordre;

    @ManyToOne
    @JoinColumn(name = "idService") // La clé étrangère dans la table laboratoire
    private Service service;
    
    // This is a convenience method to get the service ID
    public Long getIdService() {
        return service != null ? service.getIdService() : null;
    }

    @Column(name = "tarifOrganismeAdministratif")
    private double tarifOrganismeAdministratif;

    @Column(name = "tarifOrganismeCommercial")
    private double tarifOrganismeCommercial;

    @Column(name = "desactivation", columnDefinition = "BIT(1)")
    private boolean desactivation;

    @Column(name = "disponibilite", columnDefinition = "BIT(1)")
    private boolean disponibilite = true;  // Default value handled in Java

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        this.dateDerniereModification = LocalDateTime.now();
    }
}
