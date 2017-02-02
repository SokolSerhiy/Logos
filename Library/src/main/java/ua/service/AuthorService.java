package ua.service;

import java.util.List;

import ua.entity.Author;

public interface AuthorService {

	Author findOne(int id);
	
	List<Author> findAll();
	
	void delete(int id);
	
	void save(Author entity);
}
