package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	@ManyToMany
	@JoinTable(name="author_book",
	joinColumns=@JoinColumn(name="id_book"),
	inverseJoinColumns=@JoinColumn(name="id_author"))
	private List<Author> authors1 = new ArrayList<>();
	@ManyToMany
	private List<Author> authors2 = new ArrayList<>();
	@OneToMany(mappedBy="book")
	private List<AuthorBook> authorBooks = new ArrayList<>();
	
	public Book() {
	}

	public Book(String title) {
		this.title = title;
	}
	
	public List<Author> getAuthors1() {
		return authors1;
	}

	public void setAuthors1(List<Author> authors1) {
		this.authors1 = authors1;
	}

	public List<Author> getAuthors2() {
		return authors2;
	}

	public void setAuthors2(List<Author> authors2) {
		this.authors2 = authors2;
	}

	public List<AuthorBook> getAuthorBooks() {
		return authorBooks;
	}

	public void setAuthorBooks(List<AuthorBook> authorBooks) {
		this.authorBooks = authorBooks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
