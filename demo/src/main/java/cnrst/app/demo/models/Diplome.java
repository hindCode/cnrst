package cnrst.app.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "diplome")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diplome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDiplome")
    private Long idDiplome;

    @Column(name = "dateDerniereModification")
    @Temporal(TemporalType.TIMESTAMP)//indique que le champ Date doit être stocké en base de données avec à la fois la date et l'heure.
    private Date dateDerniereModification;

    @Column(name = "abreviation")
    private String abreviation;

    @Column(name = "intitule")
    private String intitule;

    @Column(name = "ordre")
    private int ordre;
}
