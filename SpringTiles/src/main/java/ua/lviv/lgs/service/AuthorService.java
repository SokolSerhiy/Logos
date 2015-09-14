package ua.lviv.lgs.service;

import java.util.List;

import ua.lviv.lgs.domain.Author;

public interface AuthorService {
	void insertAuthor (String name, int age);
	
	List<Author> getAllAuthors();
	
	Author findByName(String name);

	List<Author> findByAgePeriod(int from, int till);
}
