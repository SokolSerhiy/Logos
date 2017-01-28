package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Amount;

public interface AmountRepository extends JpaRepository<Amount, Long>{
	@Query("SELECT a FROM Amount a LEFT JOIN FETCH a.system LEFT JOIN FETCH a.ingredient WHERE a.id = ?1")
	Amount findOne(Long id);
	@Query("SELECT a FROM Amount a LEFT JOIN FETCH a.system LEFT JOIN FETCH a.ingredient")
	List<Amount> findAll();
}
