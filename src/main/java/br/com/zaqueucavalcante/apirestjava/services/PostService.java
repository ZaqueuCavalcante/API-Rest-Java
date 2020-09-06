package br.com.zaqueucavalcante.apirestjava.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zaqueucavalcante.apirestjava.entities.Post;
import br.com.zaqueucavalcante.apirestjava.repositories.PostRepository;
import br.com.zaqueucavalcante.apirestjava.resources.utils.CustomDate;
import br.com.zaqueucavalcante.apirestjava.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ResourceNotFoundException("Post not found."));
	}
	
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	public List<Post> findbyBody(String text) {
		List<Post> postsList = postRepository.findByBodyContainingIgnoreCase(text);
		return postsList;
	}
	
	public List<Post> findTextBetweenDateInterval(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() * CustomDate.millisecondsInOneDay);
		List<Post> postsList = postRepository.findTextBetweenDateInterval(text, minDate, maxDate);
		return postsList;
	}
}
