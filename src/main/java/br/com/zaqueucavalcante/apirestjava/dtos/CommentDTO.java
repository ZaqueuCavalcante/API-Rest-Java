package br.com.zaqueucavalcante.apirestjava.dtos;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Date date;
	private String body;
	private AuthorDTO author;
	
	public CommentDTO() { }

	public CommentDTO(Date date, String body, AuthorDTO author) {
		super();
		this.date = date;
		this.body = body;
		this.author = author;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
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
}