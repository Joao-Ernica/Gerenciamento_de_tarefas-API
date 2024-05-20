package org.example.service;

import org.example.entities.User;
import org.example.entities.enums.UserFunction;
import org.example.repository.UserRepository;
import org.example.service.exception.DatabaseException;
import org.example.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); // se não puder lançar o get ira lançar uma excepetions
	}

	public List<User> findByFunction(UserFunction function) {
		return repository.findByFunction(function);
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

//	public User update(long id, User obj) {
//		try {
//			User entity = repository.getReferenceById(id); //prepara o objeto e depois efetuar uma operação com o bando de dados
//			if(obj.getFinalizationDate() != null && obj.getFinalizationDate().isBefore(ChronoLocalDate.from(entity.getRegistrationDate()))) {
//				throw new IllegalArgumentException("Data de finalização não pode ser anterior à data de cadastro");
//			}
//			updateData(entity, obj);
//			return repository.save(entity);
//		} catch (EntityNotFoundException e) { // quando a entidade acessada não existe
//			throw new ResourceNotFoundException(id);
//		}
//	}

//	private void updateData(User entity, User obj) {
//		if(obj.getTitle() != null) {
//			entity.setTitle(obj.getTitle());
//		}
//		if(obj.getDescription() != null) {
//			entity.setDescription(obj.getDescription());
//		}
//		if(obj.getTaskStatus() != null) {
//			entity.setTaskStatus(obj.getTaskStatus());
//		}
//		if(obj.getFinalizationDate() != null) {
//			entity.setFinalizationDate(obj.getFinalizationDate());
//		}
//	}

	public User insert(User obj) { //metodo basico para inserir no banco de dados um novo User
		return repository.save(obj);
	}

}
