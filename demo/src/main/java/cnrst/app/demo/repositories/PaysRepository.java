package cnrst.app.demo.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cnrst.app.demo.models.Pays;

@Repository
public interface PaysRepository extends CrudRepository<Pays, Integer> {
	List<Pays> findByIntituleContainingIgnoreCase(String intitule);
}

