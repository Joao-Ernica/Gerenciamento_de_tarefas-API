package API.service;

import API.entities.User;
import API.entities.enums.UserFunction;
import API.repository.UserRepository;
import API.service.exception.DatabaseException;
import API.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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

	public User update(long id, User obj) {
		try {
			User entity = repository.getReferenceById(id); //prepara o objeto e depois efetuar uma operação com o bando de dados
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) { // quando a entidade acessada não existe
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		if(obj.getName()!= null) {
			entity.setName(obj.getName());
		}
		if(obj.getEmail() != null) {
			entity.setEmail(obj.getEmail());
		}
		if(obj.getPassword() != null) {
			entity.setPassword(obj.getPassword());
		}
		if(obj.getTeam() != null) {
			entity.setTeam(obj.getTeam());
		}
	}

	public User insert(User obj) { //metodo basico para inserir no banco de dados um novo User
		return repository.save(obj);
	}

}
