package API.entities;

import API.entities.enums.UserFunction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) //para gerar a data automaticamente
@Builder
@Entity
@Table(name = "user_tb")
public class User implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Setter(AccessLevel.NONE)
	private Long id;

	@Enumerated(EnumType.STRING)
	UserFunction function;

	private String name;

	@Setter(AccessLevel.NONE)
	private Integer cpf;
	private String email;

	@ManyToOne
	@JoinColumn(name = "team_id")
	@Setter(AccessLevel.NONE)
	private Team team;

	@Getter(onMethod = @__({@JsonIgnore}))
	@Setter(AccessLevel.NONE)
	private String password;
}
