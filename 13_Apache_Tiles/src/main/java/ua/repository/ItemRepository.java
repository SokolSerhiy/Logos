package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	@Query("SELECT i FROM Item i LEFT JOIN FETCH i.category LEFT JOIN FETCH i.producer")
	List<Item> findAll();
}
