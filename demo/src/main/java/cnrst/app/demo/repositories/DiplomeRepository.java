package cnrst.app.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cnrst.app.demo.models.Diplome;

@Repository
public interface DiplomeRepository extends JpaRepository<Diplome, Long> {
	 List<Diplome> findByIntituleContainingIgnoreCase(String intitule);
}
