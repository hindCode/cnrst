package cnrst.app.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cnrst.app.demo.models.Laboratoire;


public interface LaboratoireRepository extends JpaRepository<Laboratoire, Long> {
	List<Laboratoire> findByIntituleContainingIgnoreCase(String intitule);
	//List<Laboratoire> findByServiceId(Long serviceId);
	@Query("SELECT l FROM Laboratoire l WHERE l.service.idService = :serviceId")
	List<Laboratoire> findByServiceId(@Param("serviceId") Long serviceId);
}
