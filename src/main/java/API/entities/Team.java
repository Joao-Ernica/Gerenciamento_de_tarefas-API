package API.entities;

import API.entities.enums.TeamFunction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "team_tb")
public class Team implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Setter(AccessLevel.NONE) // proteção extra
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private TeamFunction function;

	@OneToMany(mappedBy = "team") // corrigir e buscar o problema de ter que colocar o time quando cria o user
	@Setter(AccessLevel.NONE)//para ter apenas get com o lombok
	@Getter(onMethod = @__({@JsonIgnore}))// gerar o JsonIgnore no getter do lombok
	private Set<User> user = new HashSet<>();

	public Team() {
	}

	@Builder
	public Team(String name,TeamFunction function) {
		this.name = name;
		this.function = function;
	}

}
