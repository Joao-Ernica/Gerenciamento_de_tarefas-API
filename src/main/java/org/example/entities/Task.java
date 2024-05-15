package org.example.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "tank")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	private LocalDate dateOfConclusion;

	public Task(Long id, String title, String description, LocalDate dateOfConclusion) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dateOfConclusion = dateOfConclusion;
	}
}
