package cnrst.app.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "moyenpaiement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoyenPaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMoyenPaiement")
    private Long idMoyenPaiement;

    @Column(name = "dateDerniereModification")
    private LocalDateTime dateDerniereModification;

    @Column(name = "abreviation", length = 255)
    private String abreviation;

    @Column(name = "intitule", length = 255)
    private String intitule;

    @Column(name = "ordre", nullable = false)
    private int ordre;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        this.dateDerniereModification = LocalDateTime.now();
    }
}
