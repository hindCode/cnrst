package cnrst.app.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cnrst.app.demo.models.StatutFaisabiliteDemandeDevis;

@Repository
public interface StatutFaisabiliteDemandeDevisRepository extends JpaRepository<StatutFaisabiliteDemandeDevis, Integer> {
	List<StatutFaisabiliteDemandeDevis> findByIntituleContainingIgnoreCase(String intitule);
}
