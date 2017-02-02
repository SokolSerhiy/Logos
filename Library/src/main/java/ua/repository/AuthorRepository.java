package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

	@Query("SELECT a FROM Author a LEFT JOIN FETCH a.country")
	List<Author> findAll();
	
	@Query("SELECT a FROM Author a LEFT JOIN FETCH a.country WHERE a.id = ?1")
	Author findOne(Integer id);
}
