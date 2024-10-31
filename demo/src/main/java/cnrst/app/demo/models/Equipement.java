package cnrst.app.demo.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "equipement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipement")
    private Long idEquipement;

    @Column(name = "dateDerniereModification")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDerniereModification;

    @Column(name = "natureEquipement")
    private String natureEquipement;

    @Column(name = "ordre")
    private String ordre;

    @Column(name = "designation")
    private String designation;

    @Column(name = "marque")
    private String marque;

    @Column(name = "reference")
    private String reference;

    @ManyToOne
    @JoinColumn(name = "idStatutEquipement")
    private StatutEquipement statutEquipement;

}

