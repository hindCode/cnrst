package cnrst.app.demo.models;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class AppUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	private String lastName;
	
	@Column(unique = true , nullable = false)
	private String email;
	
	private String phone;
	private String address;
	private String password;
	private String role;
	private Date createdAt;
	
}
