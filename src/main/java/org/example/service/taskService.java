package org.example.service;

import jakarta.persistence.EntityNotFoundException;
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

	public Task update(long id, Task obj) {
		try {
			Task entity = repository.getReferenceById(id); //prepara o objeto e depois efetuar uma operação com o bando de dados
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) { // quando a entidade acessada não existe
			throw new ResourceNotFoundException(id);
		}
	}

//	private void updateData(Task entity, Task obj) { //atualiza os dados da entity com os novos dados fornecidos pelo obj
//		entity.setNome(obj.getNome());
//		entity.setEmail(obj.getEmail());
//		entity.setTelefone(obj.getTelefone());
//	}

	public Task insert(Task obj) {
		return repository.save(obj);
	}

}
