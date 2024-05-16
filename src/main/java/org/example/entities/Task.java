package org.example.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.example.entities.enums.TaskStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class) //para gerar a data automaticamente
public class Task implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Setter(AccessLevel.NONE) // proteção extra
	private Long id;

	private String title;
	private String description;

	@CreatedDate
	@Column(updatable = false) //garente que depois de criada essa coluna não pode mais ser alterada
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime registrationDate;

	@JsonFormat(pattern = "dd/MM/yyyy") //LocalDate se não for usar horas
	private LocalDate finalizationDate;

	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;

	public Task() {
	}

	@Builder //teste
	public Task(String title, String description, TaskStatus taskStatus, LocalDate finalizationDate) {
		this.title = title;
		this.description = description;
		this.taskStatus = taskStatus;
		this.finalizationDate = finalizationDate;
	}
}
