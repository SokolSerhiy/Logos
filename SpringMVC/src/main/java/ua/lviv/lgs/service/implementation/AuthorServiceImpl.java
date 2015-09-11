package ua.lviv.lgs.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.AuthorDao;
import ua.lviv.lgs.domain.Author;
import ua.lviv.lgs.service.AuthorService;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService{
	
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
