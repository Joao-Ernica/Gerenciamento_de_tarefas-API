package API.entities.response;

import API.entities.Task;
import API.entities.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;

	private static TaskResponse of(Task task){
		var response = new TaskResponse();
		response.setTitle(task.getTitle());
		return response;

	}

}
