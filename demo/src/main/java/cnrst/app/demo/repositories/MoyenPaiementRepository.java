package cnrst.app.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cnrst.app.demo.models.MoyenPaiement;

public interface MoyenPaiementRepository extends JpaRepository<MoyenPaiement, Long> {
	List<MoyenPaiement> findAll();
	List<MoyenPaiement> findByIntituleContainingIgnoreCase(String intitule);

}
