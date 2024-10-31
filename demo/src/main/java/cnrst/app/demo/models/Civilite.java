package cnrst.app.demo.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "civilite")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Civilite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCivilite")
    private Long id;

    @Column(name = "dateDerniereModification")
    private Date dateDerniereModification;

    @Column(name = "abreviation", length = 255)
    private String abreviation;

    @Column(name = "intitule", length = 255)
    private String intitule;

    @Column(name = "ordre", nullable = false)
    private int ordre;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        dateDerniereModification = new Date();
    }
}
