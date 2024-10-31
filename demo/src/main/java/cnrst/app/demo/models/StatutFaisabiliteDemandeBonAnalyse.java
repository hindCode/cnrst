package cnrst.app.demo.models;


import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "statutfaisabilitedemandebonanalyse")
public class StatutFaisabiliteDemandeBonAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idStatutFaisabiliteDemandeBonAnalyse")
    private Integer id;

    @Column(name = "dateDerniereModification")
    private Date dateDerniereModification;

    @Column(name = "abreviation")
    private String abreviation;

    @Column(name = "intitule")
    private String intitule;

    @Column(name = "ordre")
    private int ordre;
}
