package cnrst.app.demo.repositories;

import cnrst.app.demo.models.MoyensContact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoyensContactRepository extends JpaRepository<MoyensContact, Long> {
	List<MoyensContact> findByIntituleContainingIgnoreCase(String intitule);
}
