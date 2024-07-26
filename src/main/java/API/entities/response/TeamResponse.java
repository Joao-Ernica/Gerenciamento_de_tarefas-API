package API.entities.response;

import API.entities.enums.TeamFunction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamResponse {

	private String name;
	private TeamFunction function;

}
