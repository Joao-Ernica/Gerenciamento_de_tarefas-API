package API.entities.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskResponse {

	private String title;
	private String description;
	private LocalDateTime registrationDate;
	private LocalDate finalizationDate;

}
