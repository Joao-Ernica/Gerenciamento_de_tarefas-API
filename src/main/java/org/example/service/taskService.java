package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.entities.Task;
import org.example.entities.enums.TaskStatus;
import org.example.repository.TaskRepository;
import org.example.service.exception.DatabaseException;
import org.example.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
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

	public List<Task> findTasksByStatus(TaskStatus status) {
		return repository.findByTaskStatus(status);
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

	/*
	somente permitirá o update e insert se a data de finalizão for maior que a de cadastro
	*/

	public Task update(long id, Task obj) {
		try {
			Task entity = repository.getReferenceById(id); //prepara o objeto e depois efetuar uma operação com o bando de dados
			if(obj.getFinalizationDate() != null && obj.getFinalizationDate().isBefore(ChronoLocalDate.from(entity.getRegistrationDate()))) {
				throw new IllegalArgumentException("Data de finalização não pode ser anterior à data de cadastro");
			}
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) { // quando a entidade acessada não existe
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Task entity, Task obj) {
		if(obj.getTitle() != null) {
			entity.setTitle(obj.getTitle());
		}
		if(obj.getDescription() != null) {
			entity.setDescription(obj.getDescription());
		}
		if(obj.getTaskStatus() != null) {
			entity.setTaskStatus(obj.getTaskStatus());
		}
		if(obj.getFinalizationDate() != null) {
			entity.setFinalizationDate(obj.getFinalizationDate());
		}
	}

	public Task insert(Task obj) {
		obj.setRegistrationDate(LocalDateTime.now()); //garanta que a data seja registrada antes de fazer a comparão
		if(obj.getFinalizationDate() != null && obj.getFinalizationDate().isBefore(obj.getRegistrationDate().toLocalDate())) {
			throw new IllegalArgumentException("Data de finalização não pode ser anterior à data de cadastro");
		}
		return repository.save(obj);
	}

}
