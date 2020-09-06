package br.com.zaqueucavalcante.apirestjava.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zaqueucavalcante.apirestjava.entities.Post;
import br.com.zaqueucavalcante.apirestjava.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		List<Post> postsList = postService.findAll();
		return ResponseEntity.ok().body(postsList);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);
	}
}
