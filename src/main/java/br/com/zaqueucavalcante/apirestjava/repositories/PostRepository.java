package br.com.zaqueucavalcante.apirestjava.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.zaqueucavalcante.apirestjava.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
