package org.example.controller;

import org.example.entities.Task;
import org.example.service.taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/task")
public class taskController {

	@Autowired
	private taskService taskService;

	@GetMapping
	public List<Task> findAll() {
		return taskService.findAll();
	}

	@GetMapping("{id}")// quando colocar /users/1 colocara a pessoa com id 1
	public Task findById(@PathVariable Long id) {
		return taskService.findById(id);

	}

	@DeleteMapping(("{id}"))
	public ResponseEntity<Void> delete(@PathVariable long id) {
		taskService.delete(id);
		return ResponseEntity.noContent().build(); //codigo HTTP 204
	}

}

