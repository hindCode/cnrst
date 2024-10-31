package cnrst.app.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cnrst.app.demo.models.Secteur;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long> { 
	List<Secteur> findByIntituleContainingIgnoreCase(String intitule);
}
