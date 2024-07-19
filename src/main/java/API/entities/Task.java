package API.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import API.entities.enums.TaskStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "task_tb")
@EntityListeners(AuditingEntityListener.class) //para gerar a data automaticamente
@Builder
public class Task implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private final Long id;

	private String title;
	private String description;

	@CreatedDate
	@Column(updatable = false) //garente que depois de criada essa coluna não pode mais ser alterada
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime registrationDate;

	@JsonFormat(pattern = "dd-MM-yyyy") //LocalDate se não for usar horas
	private LocalDate finalizationDate;

	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus = TaskStatus.DENTRO_DO_PRAZO; //não faz sentido enviar uma requisição fora do prazo ou cancelada

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

}
