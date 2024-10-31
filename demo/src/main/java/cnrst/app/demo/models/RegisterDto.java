package cnrst.app.demo.models;

import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class RegisterDto {

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	@Email
	private String email;
	private String phone;
	private String address;
	@Size(min = 6, message = "Minimum password length is 6 characters")
	private String password;
	private String confirmPassword;
}
