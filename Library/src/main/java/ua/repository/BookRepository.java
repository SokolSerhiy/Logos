package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("SELECT b FROM Book b LEFT JOIN FETCH b.author")
	List<Book> findAll();
	
	@Query("SELECT b FROM Book b LEFT JOIN FETCH b.author WHERE b.id = ?1")
	Book findOne(Integer id);
}
