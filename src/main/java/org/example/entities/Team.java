package org.example.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.entities.enums.TeamFunction;

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

	@OneToMany(mappedBy = "team")
	@Setter(AccessLevel.NONE)//para ter apenas get com o lombok
	private Set<User> user = new HashSet<>();

	public Team() {

	}

	@Builder
	public Team(Long id, String name,TeamFunction function) {
		this.id = id;
		this.name = name;
		this.function = function;
	}
}
