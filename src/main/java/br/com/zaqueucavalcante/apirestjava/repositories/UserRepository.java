package br.com.zaqueucavalcante.apirestjava.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.zaqueucavalcante.apirestjava.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
