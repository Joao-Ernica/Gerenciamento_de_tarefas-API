package API.config;

import API.entities.Task;
import API.entities.Team;
import API.entities.User;
import API.entities.enums.TeamFunction;
import API.entities.enums.UserFunction;
import API.repository.TaskRepository;
import API.repository.TeamRepository;
import API.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@Configuration
@EnableJpaAuditing
@Profile("test")
public class testConfig implements CommandLineRunner {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Override
	@SneakyThrows // ocultar exceções verificadas
	public void run(String... args) /*throws Exception*/ {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		var te1 = Team.builder()
				.name("time Dev 1")
				.function(TeamFunction.DEVELOPER)
				.build();

		var te2 = Team.builder()
				.name("Marketing")
				.function(TeamFunction.MARKETING)
				.build();

		var u1 = User.builder()
				.name("Felipe")
				.email("Felipe@email.com")
				.function(UserFunction.LIDER)
				.password("fe1234")
				.team(te1)
				.build();

		var u2 = User.builder()
				.name("Joao")
				.email("Joao@email.com")
				.function(UserFunction.ESTAGIARIO)
				.password("jp1234")
				.team(te1)
				.build();

		var tas1 = Task.builder()
				.title("API")
				.description("Fazer a API")
				.finalizationDate(LocalDate.parse("25/05/2024", formatter))
				.team(te1)
				.build();
		var tas2 = Task.builder()
				.title("Dormir")
				.description("Café?")
				.finalizationDate(LocalDate.parse("30/06/2024", formatter))
				.team(te1)
				.build();

		teamRepository.saveAll(Arrays.asList(te1, te2));
		taskRepository.saveAll(Arrays.asList(tas1, tas2));
		userRepository.saveAll(Arrays.asList(u1,u2));

	}
}