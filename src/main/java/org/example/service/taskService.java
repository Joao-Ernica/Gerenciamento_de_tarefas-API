package org.example.service;

import org.example.entities.Task;
import org.example.repository.TaskRepository;
import org.example.service.exception.DatabaseException;
import org.example.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class taskService {

	@Autowired
	private TaskRepository repository;

	public List<Task> findAll() {
		return repository.findAll();
	}

	public Task findById(Long id) {
		Optional<Task> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); // se não puder lançar o get ira lançar uma excepetions
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) { // deletar um recurso que não existe
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) { //violação de integridade do banco de dados
			throw new DatabaseException(e.getMessage());

		}
	}

}
