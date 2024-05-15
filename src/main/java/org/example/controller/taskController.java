package org.example.controller;

import org.example.entities.Task;
import org.example.service.taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("task")
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
}
