package ua.service;

import java.util.List;

import ua.entity.Book;

public interface BookService {

	Book findOne(int id);
	
	List<Book> findAll();
	
	void delete(int id);
	
	void save(Book entity);
}
