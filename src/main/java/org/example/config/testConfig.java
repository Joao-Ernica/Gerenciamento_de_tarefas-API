package org.example.config;

import lombok.SneakyThrows;
import org.example.entities.Task;
import org.example.entities.enums.TaskStatus;
import org.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration // falar que é uma classe de configuração
@Profile("test") //especificar que é uma configuração para test
public class testConfig implements CommandLineRunner { // exeecutar quando o programa for iniciado

	@Autowired
	private TaskRepository taskRepository;

	@Override
	@SneakyThrows // ocultar exceções verificadas
	public void run(String... args) /*throws Exception*/ { // sem a anotação SneakyThrows

		var tas1 = Task.builder()
				.id(null)
				.title("API")
				.description("Fazer a API")
				.taskStatus(TaskStatus.DENTRO_DO_PRAZO)
				.build();
		var tas2 = Task.builder()
				.id(null)
				.title("Dormir")
				.description("Café?")
				.taskStatus(TaskStatus.FORA_DO_PRAZO)
				.build();

		taskRepository.saveAll(Arrays.asList(tas1, tas2));

	}
}