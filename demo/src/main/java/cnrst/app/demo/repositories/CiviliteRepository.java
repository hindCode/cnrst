package cnrst.app.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cnrst.app.demo.models.Civilite;

@Repository
public interface CiviliteRepository extends JpaRepository<Civilite, Long> {
	  List<Civilite> findByIntituleContainingIgnoreCase(String intitule);
}
