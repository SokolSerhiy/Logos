package lv.lgs.hibernate.domain;

import java.util.List;
import java.util.Set;

public class Student {
	
	private int id;
	private String firstName;
	private String lastName;
	private List<Book> books;
	private Set<Subject> subjects;
	
	public Student() {
	}

	public Student(String firstName, String lastName, List<Book> books) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = books;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	
	
}
