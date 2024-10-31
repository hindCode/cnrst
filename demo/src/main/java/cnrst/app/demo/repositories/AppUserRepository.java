package cnrst.app.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cnrst.app.demo.models.AppUser;


public interface AppUserRepository  extends JpaRepository<AppUser, Integer>{

	public AppUser findByEmail(String email);
}
