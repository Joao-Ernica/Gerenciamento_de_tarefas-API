package API.entities.request;

import jakarta.persistence.*;
import lombok.*;
import API.entities.Team;
import API.entities.enums.UserFunction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@Enumerated(EnumType.STRING)
	UserFunction function;

	private String name;
	private Integer cpf;
	private String email;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	private String password;

}
