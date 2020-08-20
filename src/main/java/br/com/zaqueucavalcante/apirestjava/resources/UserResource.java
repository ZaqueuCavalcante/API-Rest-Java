package br.com.zaqueucavalcante.apirestjava.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zaqueucavalcante.apirestjava.dto.UserDTO;
import br.com.zaqueucavalcante.apirestjava.entities.User;
import br.com.zaqueucavalcante.apirestjava.services.UserService;


@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> usersList = userService.findAll();
		List<UserDTO> usersListDTO = usersList.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersListDTO);
	}

//	@GetMapping(value = "/{id}")
//	public ResponseEntity<User> findById(@PathVariable Long id) {
//		User user = userService.findById(id);
//		return ResponseEntity.ok().body(user);
//	}
//
//	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
//	@PostMapping
//	public ResponseEntity<User> insert(@RequestBody User user) {
//		user = userService.insert(user);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
//		return ResponseEntity.created(uri).body(user);
//	}
//
//	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<Void> delete(@PathVariable Long id) {
//		userService.delete(id);
//		return ResponseEntity.noContent().build();
//	}
//
//	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
//		User updatedUser = userService.update(id, user);
//		return ResponseEntity.ok().body(updatedUser);
//	}
}
