package br.com.zaqueucavalcante.apirestjava.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zaqueucavalcante.apirestjava.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByBodyContainingIgnoreCase(String text);
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} },"
		 + "{ $or: [ { 'body': { $regex: ?0, $options: 'i' } },"
		          + "{ 'comments.body': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> findTextBetweenDateInterval(String text, Date minDate, Date maxDate);
}
