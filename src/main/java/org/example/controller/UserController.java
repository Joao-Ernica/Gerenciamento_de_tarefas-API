package org.example.controller;

import org.example.entities.User;
import org.example.entities.enums.UserFunction;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("{id}")// quando colocar /users/1 colocara a pessoa com id 1
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	/*
	 filtro para o status
	*/

	@GetMapping("function/{function}")
	public List<User> getByFunction(@PathVariable UserFunction function) {
		return userService.findByFunction(function);
	}

	@DeleteMapping(("{id}"))
	public ResponseEntity<Void> delete(@PathVariable long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build(); //codigo HTTP 204
	}

//	@PutMapping("{id}") //usado para atualizar um recurso existente na web
//	public User update(@PathVariable Long id, @RequestBody Task obj) {
//		return userService.update(id, obj);
//	}

	/*
	 constroi a URI com a localizão do novo obj
	*/

//	@PostMapping // post serve para inserir dados
//	public ResponseEntity<User> insert(@RequestBody User obj) { //@RequestBody converter o corpo de uma requisição HTTP em um objeto Java.
//		obj = userService.insert(obj);
//		URI uri = ServletUriComponentsBuilder //constroi uma URI
//				.fromCurrentRequest()
//				.path("{id}")
//				.buildAndExpand(obj.getId())
//				.toUri();
//		return ResponseEntity.created(uri).body(obj);// criado e um código de status HTTP 201, usado para indicar o sucesso na criação
//	}

}

