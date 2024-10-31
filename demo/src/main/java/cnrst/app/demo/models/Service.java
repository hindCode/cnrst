package cnrst.app.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idService")
    private Long idService;

    @Column(name = "dateDerniereModification")
    private LocalDateTime dateDerniereModification;

    @Column(name = "abreviation", length = 255)
    private String abreviation;

    @Column(name = "intitule", length = 255)
    private String intitule;

    @Column(name = "ordre", nullable = false)
    private int ordre;

    @Column(name = "consommationPartielleBA", columnDefinition = "BIT(1)")
    private boolean consommationPartielleBA;

    @OneToMany(mappedBy = "service") // La clé est le nom du champ dans la classe Laboratoire
    private List<Laboratoire> laboratoires;

    @PrePersist
    @PreUpdate
    public void preUpdate() {
        this.dateDerniereModification = LocalDateTime.now();
    }
}
