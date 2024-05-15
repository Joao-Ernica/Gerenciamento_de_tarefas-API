package org.example.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.entities.enums.TaskStatus;
import java.io.Serial;
import java.io.Serializable;



@Entity
@Data
@Table(name = "task")
public class Task implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;

	@Builder //teste
	public Task(Long id, String title, String description, TaskStatus taskStatus) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.taskStatus = taskStatus;
	}
}
