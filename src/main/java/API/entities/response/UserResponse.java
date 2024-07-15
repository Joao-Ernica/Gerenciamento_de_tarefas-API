package API.entities.response;

import API.entities.User;
import API.entities.enums.UserFunction;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	@Enumerated(EnumType.STRING)
	UserFunction function;

	private String name;
	private String email;

	public static UserResponse of(User user){
		var usuarioResponse = new UserResponse();
		usuarioResponse.setName(user.getName());
		return usuarioResponse;
	}
}
