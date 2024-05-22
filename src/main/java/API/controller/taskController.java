package API.controller;

import API.entities.Task;
import API.entities.enums.TaskStatus;
import API.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("task")
public class taskController {

	@Autowired
	private TaskService taskService;

	@GetMapping
	public List<Task> findAll() {
		return taskService.findAll();
	}

	@GetMapping("{id}")// quando colocar /users/1 colocara a pessoa com id 1
	public Task findById(@PathVariable Long id) {
		return taskService.findById(id);
	}

	/*
	 filtro para o status
	*/

	@GetMapping("status/{status}")
	public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
		return taskService.findTasksByStatus(status);
	}

	@DeleteMapping(("{id}"))
	public ResponseEntity<Void> delete(@PathVariable long id) {
		taskService.delete(id);
		return ResponseEntity.noContent().build(); //codigo HTTP 204
	}

	@PutMapping("{id}") //usado para atualizar um recurso existente na web
	public Task update(@PathVariable Long id, @RequestBody Task obj) {
		return taskService.update(id, obj);
	}

	/*
	 constroi a URI com a localizão do novo obj
	*/

	/*
	DTO

	*/

	@PostMapping // post serve para inserir dados
	public ResponseEntity<Task> insert(@RequestBody Task obj) { //@RequestBody converter o corpo de uma requisição HTTP em um objeto Java.
		obj = taskService.insert(obj);
		URI uri = ServletUriComponentsBuilder //constroi uma URI
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).body(obj);// criado e um código de status HTTP 201, usado para indicar o sucesso na criação
	}

}

