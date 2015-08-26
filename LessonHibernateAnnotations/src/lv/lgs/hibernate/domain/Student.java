package lv.lgs.hibernate.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	//вказує, що поле нижче є відображенням зв`зку один до багатьох
	//з цієї сторони зв`язку, тобто в анотації OneToMany, завжди необхідно описати додаткові параметри
	//cascade опціональний параметр, який вказує на поведінку БД в випадку видалення, оновлення, створення елементів
	//fetch зазвичай обов`язковий, оскільки по замовчуванню витягує з БД весь ланцюжок з повною колекцією
	//mappedBy обов`язковий, вказує на сторону відповідальну за зв`язок
	//ссилається завжди на поле з іншої сторони яке приймає за параметр об`єкт цього класу, див. Book
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
	private List<Book> books;
	//вказує, що поле нижче є відображенням зв`зку багато до багатьох
	//анотація JoinTable вказує на те, що потрібно приєднати таблицю
	//проте, ця анотація викликана і з протилежної сторони зв`язку
	//тому буде створена окрема таблиця з 2 стовпцями
	//joinColumns створює стовпець з FK цього класу
	//inverseJoinColumns створює стовпець з FK класу з протилежної сторони зв`язку
	//ВАЖЛИВО! з протилежної сторони повинен бути ідентичний опис, 
	//зміни повинні бути лише в joinColumns i inverseJoinColumns див. Subject
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "student_subject", joinColumns = 
	@JoinColumn(name = "fk_student"), inverseJoinColumns = 
	@JoinColumn(name = "fk_subject"))
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
