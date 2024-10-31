package cnrst.app.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cnrst.app.demo.models.ChampFicheAnalyse;


@Repository
public interface ChampFicheAnalyseRepository extends JpaRepository<ChampFicheAnalyse, Long> {
	List<ChampFicheAnalyse> findByIntituleContainingIgnoreCase(String intitule);
}