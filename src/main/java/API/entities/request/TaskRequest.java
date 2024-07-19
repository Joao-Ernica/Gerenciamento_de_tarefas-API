package API.entities.request;

import API.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequest {

	private String title;
	private String description;
	private LocalDate finalizationDate;
	private Team team;
	
}
