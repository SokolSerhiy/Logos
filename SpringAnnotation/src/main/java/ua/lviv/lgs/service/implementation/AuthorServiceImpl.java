package ua.lviv.lgs.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.AuthorDao;
import ua.lviv.lgs.domain.Author;
import ua.lviv.lgs.service.AuthorService;

//This annotation tells Spring context to create Service Bean
//Ця анотація вказує Spring context створити Сервіс Bean. Клас повинен імплементувати Service інтерфейс, в якому оголошені необхідні методи.

//Service layer designed to work with application business logic (parsing, creating DTO, calculating etc.).
// Сервісний рівень призначений для роботи з бізнес логікою програми (парсінг, створення DTO, необхідні обчислення і т.д.)
@Service("authorService")
public class AuthorServiceImpl implements AuthorService{
	
	//This annotation takes bean of specified type from Spring context and set it to the class field.
	// Ця анотація бере bean визначеного типу (AuthorDao в цьому випадку) зі Spring context і встановлює його в поле класу.
	@Autowired
	private AuthorDao dao;

	public void insertAuthor(String name, int age) {
		Author a = new Author(name, age);
		dao.insertAuthor(a);
	}

	public List<Author> getAllAuthors() {
		// TODO Auto-generated method stub
		return dao.getAllAuthors();
	}

	public Author findByNameAndAge(String name, int age) {
		// TODO Auto-generated method stub
		return dao.findByNameAndAge(name, age);
	}
	
	
}
