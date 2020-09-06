package br.com.zaqueucavalcante.apirestjava.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zaqueucavalcante.apirestjava.entities.Post;
import br.com.zaqueucavalcante.apirestjava.resources.utils.CustomDate;
import br.com.zaqueucavalcante.apirestjava.resources.utils.EncodeURI;
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
	
	@GetMapping(value = "/body")
	public ResponseEntity<List<Post>> findByBodyName(@RequestParam(value="text", defaultValue="") String text) {
		text = EncodeURI.decode(text);
		List<Post> postsList = postService.findbyBody(text);
		return ResponseEntity.ok().body(postsList);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> findByText(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxdate", defaultValue="") String maxDate) {
		text = EncodeURI.decode(text);
		Date min = CustomDate.convert(minDate, new Date(0L));
		Date max = CustomDate.convert(maxDate, new Date());
		List<Post> postsList = postService.findTextBetweenDateInterval(text, min, max);
		return ResponseEntity.ok().body(postsList);
	}
}
