package API.controller;

import API.entities.request.UserRequest;
import API.entities.response.UserResponse;
import API.mapper.UserMapping;
import API.service.UserService;
import API.entities.User;
import API.entities.enums.UserFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private final UserService service;

	@Autowired
	private final UserMapping mapping;

	@GetMapping
	public List<UserResponse> findAll() {
		List<User> user = service.findAll();
		return mapping.toUserResponseList(user); //utilizando ModelMapper
	}

	@GetMapping("{id}")
	public UserResponse findById(@PathVariable Long id) {
		User user = service.findById(id);
		return mapping.toUserResponse(user);
	}

	/*
	 filtro para a function do usuario
	*/

	@GetMapping("function/{function}")
	public List<UserResponse> getByFunction(@PathVariable UserFunction function) {
		List<User> user = service.findByFunction(function);
		return mapping.toUserResponseList(user);
	}

	@DeleteMapping(("{id}"))
	public ResponseEntity<Void> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.noContent().build(); //codigo HTTP 204
	}

	@PutMapping("{id}")
	public UserResponse update(@PathVariable Long id, @RequestBody UserRequest request) {
		User user = mapping.toUser(request);
		User userToUpdate = service.update(id, user);
		return mapping.toUserResponse(userToUpdate);

	}

	/*
	 constroi a URI com a localizão do novo obj
	*/

	@PostMapping
	public ResponseEntity<UserResponse> insert(@RequestBody UserRequest request) { //@RequestBody converter o corpo de uma requisição HTTP em um objeto Java.
		User user = mapping.toUser(request);
		User userInsert = service.insert(user);
		UserResponse response = mapping.toUserResponse(userInsert);

		URI uri = ServletUriComponentsBuilder //constroi uma URI
				.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(uri).body(response);// criado e um código de status HTTP 201, usado para indicar o sucesso na criação
	}


}

