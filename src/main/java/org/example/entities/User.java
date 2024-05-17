package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.entities.enums.UserFunction;
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

	UserFunction function;

	private String name;
	private String email;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@JsonIgnore
	private String password;

	public User() {

	}

	@Builder
	public User(UserFunction function, String name, String email, Team team, String password) {
		this.function = function;
		this.name = name;
		this.email = email;
		this.team = team;
		this.password = password;
	}
}
