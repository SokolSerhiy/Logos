package ua.lviv.lgs.dao;

import java.util.List;

import ua.lviv.lgs.domain.Author;

public interface AuthorDao {
	void insertAuthor(Author author);

	List<Author> getAllAuthors();

	Author findByName(String name);

	List<Author> findByAgePeriod(int from, int till);
}
