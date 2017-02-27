package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Amount;

public interface AmountRepository extends JpaRepository<Amount, Long>, JpaSpecificationExecutor<Amount>{
	@Query("SELECT a FROM Amount a LEFT JOIN FETCH a.system LEFT JOIN FETCH a.ingredient WHERE a.id = ?1")
	Amount findOne(Long id);
	@Query("SELECT a FROM Amount a LEFT JOIN FETCH a.system LEFT JOIN FETCH a.ingredient")
	List<Amount> findAll();
	@Query(value="SELECT a FROM Amount a LEFT JOIN FETCH a.system LEFT JOIN FETCH a.ingredient",
			countQuery="SELECT count(a.id) FROM Amount a")
	Page<Amount> findAll(Pageable pageable);
}
