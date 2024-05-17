package org.example.config;

import lombok.SneakyThrows;
import org.example.entities.Task;
import org.example.entities.enums.TaskStatus;
import org.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@Configuration // falar que é uma classe de configuração
@EnableJpaAuditing //necessario para gerar a data automaticamente
@Profile("test") //especificar que é uma configuração para test
public class testConfig implements CommandLineRunner { // exeecutar quando o programa for iniciado

	@Autowired
	private TaskRepository taskRepository;

	@Override
	@SneakyThrows // ocultar exceções verificadas
	public void run(String... args) /*throws Exception*/ { // sem a anotação SneakyThrows
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		var tas1 = Task.builder()
				.title("API")
				.description("Fazer a API")
				.finalizationDate(LocalDate.parse("25/05/2024", formatter))
				.build();
		var tas2 = Task.builder()
				.title("Dormir")
				.description("Café?")
				.finalizationDate(LocalDate.parse("30/06/2024", formatter))
				.build();

		taskRepository.saveAll(Arrays.asList(tas1, tas2));

	}
}