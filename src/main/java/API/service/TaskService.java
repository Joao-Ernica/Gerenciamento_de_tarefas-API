package API.service;

import API.entities.Task;
import API.repository.TaskRepository;
import API.service.exception.DatabaseException;
import API.service.exception.IllegalArgumentException;
import API.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import API.entities.enums.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class TaskService {

	@Autowired
	private final TaskRepository repository;

	@Autowired
	private final ModelMapper mapper;

	public List<Task> findAll() {
		return repository.findAll();
	}

	public Task findById(Long id) {
		return repository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException(id));
	}

	public List<Task> findTasksByStatus(TaskStatus status) {
		return repository.findByTaskStatus(status);
	}

	public List<Task> getAllTasksOrderedByData(String order) {
		order = order.toUpperCase(Locale.ROOT);
		if(order.equals("DECR")) {
			return repository.findAllByOrderByFinalizationDateDesc();
		} else if(order.equals("CRES")) {
			return repository.findAllByOrderByFinalizationDateAsc();
		}
		throw new IllegalArgumentException("Codigo de ordem fornecido esta incorreto");
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
		Task byId = repository.findById(id).orElseThrow(()
				-> new IllegalArgumentException("Id não encontrado"));

		BeanUtils.copyProperties(obj, byId, "id");
		return repository.save(byId);
	}

	public Task finalizationUpdate(Long id, LocalDate date) {
		try {
			Task entity = repository.getReferenceById(id);
			if(date != null && date.isBefore(entity.getRegistrationDate().toLocalDate())) {
				throw new IllegalArgumentException("A Nova data de finalização não pode ser null");
			}
			entity.setFinalizationDate(date);
			return repository.save(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);

		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Formato de data inválido. Esperado: dd-MM-yyyy");
		}
	}

	public Task insert(Task obj) {
		obj.setRegistrationDate(LocalDateTime.now()); //garanta que a data seja registrada antes de fazer a comparão
		if(obj.getFinalizationDate() != null && obj.getFinalizationDate().isBefore(obj.getRegistrationDate().toLocalDate())) {
			throw new IllegalArgumentException("Data de finalização não pode ser anterior à data de cadastro");
		}
		return repository.save(obj);
	}

	public List<Task> findByFinalizationDateBetween(LocalDate dataInicial, LocalDate dataFinal) {
		if(dataInicial == null || dataFinal == null) {
			throw new IllegalArgumentException("As datas devem ser válidas e não nulas");
		}
		List<Task> task = repository.findByFinalizationDateBetween(dataInicial, dataFinal);

		if(task.isEmpty()) { // verifica se a lista esta vazia
			throw new IllegalArgumentException("Não existe tarefas entre essas datas");
		}

		return task;
	}

//	@EventListener //sempre será chamado quando determinado evento acontecer
//	public void handleTaskUpdate(TaskUpdatedEvent event) {
//		Task task = event.getTask();
//		LocalDate currentDate = LocalDate.now();
//
//		if(task.getFinalizationDate().isBefore(currentDate)) {
//			task.setTaskStatus(TaskStatus.FORA_DO_PRAZO);
//			repository.save(task);
//		}
//
//	}
}