package cnrst.app.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "moyenscontact")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoyensContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMoyensContact")
    private Long idMoyensContact;

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
