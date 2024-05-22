package API.entities.response;

import API.entities.Team;
import API.entities.enums.UserFunction;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	@Id
	@Setter(AccessLevel.NONE) // proteção extra
	private Long id;

	@Enumerated(EnumType.STRING)
	UserFunction function;

	private String name;
	private String email;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
}
