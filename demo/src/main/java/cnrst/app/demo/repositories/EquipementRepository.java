package cnrst.app.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cnrst.app.demo.models.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {
	List<Equipement> findByDesignationContainingIgnoreCase(String designation);
	List<Equipement> findByMarqueContainingIgnoreCase(String marque);
	List<Equipement> findByReferenceContainingIgnoreCase(String reference);
}
