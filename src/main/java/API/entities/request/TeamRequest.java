package API.entities.request;

import API.entities.enums.TeamFunction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamRequest {

	private String name;
	private TeamFunction function;

}
