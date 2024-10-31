package cnrst.app.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cnrst.app.demo.models.StatutEquipement;

@Repository
public interface StatutEquipementRepository extends JpaRepository<StatutEquipement, Long> {
	List<StatutEquipement> findByIntituleContainingIgnoreCase(String intitule);
}
