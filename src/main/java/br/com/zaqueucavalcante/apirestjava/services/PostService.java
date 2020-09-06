package br.com.zaqueucavalcante.apirestjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zaqueucavalcante.apirestjava.entities.Post;
import br.com.zaqueucavalcante.apirestjava.repositories.PostRepository;
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
}
