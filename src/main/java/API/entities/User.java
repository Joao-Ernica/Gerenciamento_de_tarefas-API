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
@EntityListeners(AuditingEntityListener.class) //para gerar a data automaticamente
@Entity
@Table(name = "user_tb")
public class User implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Setter(AccessLevel.NONE) // proteção extra
	private Long id;

	@Enumerated(EnumType.STRING)
	UserFunction function;

	private String name;
	private Integer cpf;
	private String email;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@Getter(onMethod = @__({@JsonIgnore}))
	private String password;

	public User() {

	}

	@Builder
	public User(UserFunction function,Integer cpf, String name, String email, Team team, String password) {
		this.function = function;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.team = team;
		this.password = password;
	}
}
