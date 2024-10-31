package cnrst.app.demo.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cnrst.app.demo.models.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
	List<Service> findByIntituleContainingIgnoreCase(String intitule);
	List<Service> findByAbreviationContainingIgnoreCase(String abreviation);

}
