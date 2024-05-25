package API.controller;

import API.entities.request.UserRequest;
import API.entities.response.UserResponse;
import API.mapper.UserMapping;
import API.service.UserService;
import API.entities.User;
import API.entities.enums.UserFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserResponse> findAll() {
		List<User> user = userService.findAll();
		return user.
				stream()
				.map(UserMapping::toUserResponse)
				.collect(Collectors.toList());
	}

	@GetMapping("{id}")
	public UserResponse findById(@PathVariable Long id) {
		User user = userService.findById(id);
		return UserMapping.toUserResponse(user);
	}

	/*
	 filtro para a function do usuario
	*/

	@GetMapping("function/{function}")
	public List<UserResponse> getByFunction(@PathVariable UserFunction function) {
		List<User> user = userService.findByFunction(function);
		return user.
				stream()
				.map(UserMapping::toUserResponse)
				.collect(Collectors.toList());
	}

	@DeleteMapping(("{id}"))
	public ResponseEntity<Void> delete(@PathVariable long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build(); //codigo HTTP 204
	}

	@PutMapping("{id}") //usado para atualizar um recurso existente na web
	public User update(@PathVariable Long id, @RequestBody User obj) {
		return userService.update(id, obj);
	}

	/*
	 constroi a URI com a localizão do novo obj
	*/


	@PostMapping
	public ResponseEntity<UserResponse> insert(@RequestBody UserRequest request) { //@RequestBody converter o corpo de uma requisição HTTP em um objeto Java.
		User user = UserMapping.toUser(request);
		User userInsert = userService.insert(user);
		UserResponse userResponse = UserMapping.toUserResponse( userInsert);

		URI uri = ServletUriComponentsBuilder //constroi uma URI
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(uri).body(userResponse);// criado e um código de status HTTP 201, usado para indicar o sucesso na criação
	}



}

