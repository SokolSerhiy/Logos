package lv.lgs.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//показує, що клас буде відображено у БД
@Entity
public class Book {
	//показує, що поле нижще буде виконувати роль стовпця id
	@Id
	//вказує яким генератором скористатись при генерації поля нижче
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//показує, що поле нижче буде стовпцем,
	//параметр вказує як буде називатись стовпець в БД
	@Column(name = "name")
	private String name;
	//вказує, що поле нижче є відображенням зв`зку багато до одного
	@ManyToOne
	//вказує на те що необхідно створити додатковий стовпець, який буде FK
	//до протилежної сторони зв`язку
	@JoinColumn
	private Student student;
	
	public Book() {
	}

	public Book(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
