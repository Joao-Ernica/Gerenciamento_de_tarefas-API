package org.example.service;

import org.example.entities.Task;
import org.example.repository.taskRepository;
import org.example.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class taskService {

	@Autowired
	private taskRepository repository;

	public List<Task> findAll() { //serviço repassando um repositório para o controlador
		return repository.findAll(); //metodo para mostrar todas as tasks
	}

	public Task findById(Long id) {
		Optional<Task> obj = repository.findById(id); //Optional, ela pode conter um valor presente (ou seja, o objeto que você está buscando) ou um valor ausente (que é representado por null
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); // se não puder lançar o get ira lançar uma excepetions
	}
}
