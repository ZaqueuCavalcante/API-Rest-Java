package br.com.zaqueucavalcante.apirestjava.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.zaqueucavalcante.apirestjava.dtos.AuthorDTO;
import br.com.zaqueucavalcante.apirestjava.dtos.CommentDTO;

@Document(collection = "post")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Date date;
	private String body;
	private AuthorDTO author;

	private List<CommentDTO> comments = new ArrayList<>();

	public Post() {	}

	public Post(String id, Date date, String body, AuthorDTO author) {
		super();
		this.id = id;
		this.date = date;
		this.body = body;
		this.author = author;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	public String getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getBody() {
		return body;
	}

	public void editBody(String body) {
		this.body = body;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void addComment(CommentDTO comment) {
		this.comments.add(comment);
	}
	
	public void addComments(List<CommentDTO> commentsList) {
		this.comments.addAll(commentsList);
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
