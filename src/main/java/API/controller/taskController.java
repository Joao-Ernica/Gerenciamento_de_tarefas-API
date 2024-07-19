package API.controller;

import API.entities.Task;
import API.entities.enums.TaskStatus;
import API.entities.request.TaskRequest;
import API.entities.response.TaskResponse;
import API.mapper.TaskMapping;
import API.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor
public class taskController {

	@Autowired
	private TaskService service;

	@Autowired
	private final TaskMapping mapping;

	@GetMapping
	public List<TaskResponse> findAll() {
		List<Task> task = service.findAll();
		return mapping.toTaskResponseList(task);
	}

	@GetMapping("{id}")// quando colocar /users/1 colocara a pessoa com id 1
	public TaskResponse findById(@PathVariable Long id) {
		Task task = service.findById(id);
		return mapping.toTaskResponse(task);
	}

	/*
	 filtro para o status
	*/

	@GetMapping("status/{status}")
	public List<TaskResponse> getTasksByStatus(@PathVariable TaskStatus status) {
		List<Task> task = service.findTasksByStatus(status);
		return mapping.toTaskResponseList(task);
	}

	@DeleteMapping(("{id}"))
	public ResponseEntity<Void> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.noContent().build(); //codigo HTTP 204
	}

	@PutMapping("{id}") //usado para atualizar um recurso existente na web
	public TaskResponse update(@PathVariable Long id, @RequestBody TaskRequest obj) {
		Task request = mapping.toTask(obj);
		Task task = service.update(id, request);
		return mapping.toTaskResponse(task);
	}

	/*
	 constroi a URI com a localizão do novo obj
	*/

	@PostMapping // post serve para inserir dados
	public ResponseEntity<TaskResponse> insert(@RequestBody TaskRequest obj) { //@RequestBody converter o corpo de uma requisição HTTP em um objeto Java.
	Task request = mapping.toTask(obj);
	Task task = service.insert(request);
	TaskResponse response = mapping.toTaskResponse(task);

		URI uri = ServletUriComponentsBuilder //constroi uma URI
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(task.getId())
				.toUri();
		return ResponseEntity.created(uri).body(response);
	}

	@GetMapping("finalizationDate/{order}")
	public List<TaskResponse> getAllTasksOrderedByFinalizationDate(@PathVariable String order) {
		List<Task> task = service.getAllTasksOrderedByData(order);
		return mapping.toTaskResponseList(task);
	}

	@GetMapping("{dataInicial}/{dataFinal}")
	public List<TaskResponse> filterTaskByData(@PathVariable @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate dataInicial,
										@PathVariable @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate dataFinal){
		List<Task> task = service.findByFinalizationDateBetween(dataInicial, dataFinal);
		return mapping.toTaskResponseList(task);
	}

}

