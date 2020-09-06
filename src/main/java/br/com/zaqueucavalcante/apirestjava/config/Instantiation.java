package br.com.zaqueucavalcante.apirestjava.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.zaqueucavalcante.apirestjava.dtos.AuthorDTO;
import br.com.zaqueucavalcante.apirestjava.dtos.CommentDTO;
import br.com.zaqueucavalcante.apirestjava.entities.Post;
import br.com.zaqueucavalcante.apirestjava.entities.User;
import br.com.zaqueucavalcante.apirestjava.repositories.PostRepository;
import br.com.zaqueucavalcante.apirestjava.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userReposiroty;

	@Autowired
	private PostRepository postReposiroty;

	@Override
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiroty.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userReposiroty.saveAll(Arrays.asList(maria, alex, bob));

		postReposiroty.deleteAll();
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Acordei feliz hoje!", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO(sdf.parse("21/03/2018"), "Boa viagem mano!", new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO(sdf.parse("22/03/2018"), "Aproveite", new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO(sdf.parse("23/03/2018"), "Tenha um ótimo dia!", new AuthorDTO(alex));
		
		post1.addComments(Arrays.asList(c1, c2));
		post2.addComments(Arrays.asList(c3));
		postReposiroty.saveAll(Arrays.asList(post1, post2));
		
		maria.addPosts(Arrays.asList(post1, post2));
		userReposiroty.save(maria);
	}
}
