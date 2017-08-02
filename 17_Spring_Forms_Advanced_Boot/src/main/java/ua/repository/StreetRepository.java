package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Street;

public interface StreetRepository extends JpaRepository<Street, Integer>{

	@Query("SELECT s.name FROM Street s")
	List<String> findNames();

	Street findByName(String name);

}
