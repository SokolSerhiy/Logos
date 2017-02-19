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
public class Author {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	//Отже 3 варіанти роботи з зв'язком
	//умовно назву їх 1, 2 і 3
	//по порядку нижче
	//перший схожий на костиль
	//оскільки Hibernate думає що створив 2 таблиці
	//насправді створив одну
	//цей підхід найпростіший для початку вивчення
	//цього виду зв'язку
	@ManyToMany
	@JoinTable(name="author_book",
	joinColumns=@JoinColumn(name="id_author"),
	inverseJoinColumns=@JoinColumn(name="id_book"))
	private List<Book> books1 = new ArrayList<>();
	//Другий варіант дещо складніший
	//оскільки заставляє задуматись над майбутнім,
	//що на ранніх етапах вивчення
	//буває вельми складно
	//справа в тому, що через наявність
	//mappedBy список, що нижче не несе відповідальності
	//за зв'язок, тобто скільки я б не додавав в цей список
	//книжок зв'язок в БД не з'явиться
	//Складність полягає в тому, що ще на етапі проектування
	//потрібно вирішити який зі списків відповідальний за
	//цей зв'язок, тоді над іншим поставити mappedBy
	@ManyToMany(mappedBy="authors2")
	private List<Book> books2 = new ArrayList<>();
	//І останній варіант, рекомендований Hibernate
	//відсутність самого зв'язку, за допомогою ще однієї
	//ентіті, яка відображає таблицю з зв'язком
	//В main розглянемо нюанси роботи з кожним з них
	@OneToMany(mappedBy="author")	
	private List<AuthorBook> authorBooks = new ArrayList<>();
	
	public Author() {
	}

	public Author(String name) {
		this.name = name;
	}
	
	public List<Book> getBooks1() {
		return books1;
	}

	public void setBooks1(List<Book> books1) {
		this.books1 = books1;
	}

	public List<Book> getBooks2() {
		return books2;
	}

	public void setBooks2(List<Book> books2) {
		this.books2 = books2;
	}

	public List<AuthorBook> getAuthorBooks() {
		return authorBooks;
	}

	public void setAuthorBooks(List<AuthorBook> authorBooks) {
		this.authorBooks = authorBooks;
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
	
	
}
