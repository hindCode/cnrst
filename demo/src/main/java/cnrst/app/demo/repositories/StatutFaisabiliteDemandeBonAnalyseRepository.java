package cnrst.app.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cnrst.app.demo.models.StatutFaisabiliteDemandeBonAnalyse;

public interface StatutFaisabiliteDemandeBonAnalyseRepository extends JpaRepository<StatutFaisabiliteDemandeBonAnalyse , Integer> {
	List<StatutFaisabiliteDemandeBonAnalyse> findByIntituleContainingIgnoreCase(String intitule);
}
