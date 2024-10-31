package cnrst.app.demo.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "statutequipement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatutEquipement {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "idStatutEquipement")
	    private Long idStatutEquipement;

	    @Column(name = "dateDerniereModification")
	    private LocalDateTime dateDerniereModification; // Consider using LocalDateTime

	    @Column(name = "abreviation")
	    @NotNull
	    @Size(max = 50) // Example constraint
	    private String abreviation;

	    @Column(name = "intitule")
	    @NotNull
	    @Size(max = 100) // Example constraint
	    private String intitule;

	    @Column(name = "ordre")
	    private Integer ordre;
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

}