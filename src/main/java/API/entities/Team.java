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
@AllArgsConstructor
@Builder
@Entity
@Table(name = "team_tb")
public class Team implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private final Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private TeamFunction function;

	@OneToMany(mappedBy = "team") // corrigir e buscar o problema de ter que colocar o time quando cria o user
	@Getter(onMethod = @__({@JsonIgnore}))// gerar o JsonIgnore no getter do lombok
	private final Set<User> user = new HashSet<>();

	@OneToMany(mappedBy = "team")
	@Getter(onMethod = @__({@JsonIgnore}))// gerar o JsonIgnore no getter do lombok
	private final Set<Task> task = new HashSet<>();

}
