package br.com.zaqueucavalcante.apirestjava.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zaqueucavalcante.apirestjava.dtos.UserDTO;
import br.com.zaqueucavalcante.apirestjava.entities.Post;
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
		List<User> userList = userService.findAll();
		List<UserDTO> userDTOList = userList.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(userDTOList);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.findById(id);
		UserDTO userDTO = new UserDTO(user);
		return ResponseEntity.ok().body(userDTO);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
		User user = userService.fromDTO(userDTO);
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {
		User updatedUser = userService.update(id, user);
		return ResponseEntity.ok().body(updatedUser);
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findAllPosts(@PathVariable String id) {
		User user = userService.findById(id);
		List<Post> userPosts = user.getPosts();
		return ResponseEntity.ok().body(userPosts);
	}
}
