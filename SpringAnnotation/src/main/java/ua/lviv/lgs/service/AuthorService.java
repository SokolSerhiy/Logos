package ua.lviv.lgs.service;

import java.util.List;

import ua.lviv.lgs.domain.Author;

public interface AuthorService {
	void insertAuthor(String name, int age);

	List<Author> getAllAuthors();

	Author findByNameAndAge(String name, int age);
}
