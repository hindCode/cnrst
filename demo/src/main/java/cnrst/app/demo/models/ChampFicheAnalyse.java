package cnrst.app.demo.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "champficheanalyse")

public class ChampFicheAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idChampFicheAnalyse")
    private Long id;

    @Column(name = "dateDerniereModification")
    private Date dateDerniereModification;

    @Column(name = "abreviation", length = 255)
    private String abreviation;

    @Column(name = "intitule", length = 255)
    private String intitule;

    @Column(name = "ordre", nullable = false)
    private int ordre;

    @ManyToOne
    @JoinColumn(name = "idLaboratoire")
    private Laboratoire laboratoire;
    // Ajoutez cette méthode pour obtenir l'identifiant du laboratoire
    public Long getIdLaboratoire() {
        return laboratoire != null ? laboratoire.getIdLaboratoire() : null;
    }

   
}
