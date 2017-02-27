package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
	@Query("SELECT DISTINCT r FROM Recipe r JOIN FETCH r.amounts a LEFT JOIN FETCH a.system LEFT JOIN FETCH a.ingredient")
	List<Recipe> findAll();
	@Query("SELECT DISTINCT r FROM Recipe r JOIN FETCH r.amounts a LEFT JOIN FETCH a.system LEFT JOIN FETCH a.ingredient WHERE r.id=?1")
	Recipe findOne(Long id);
	@Query(value="SELECT DISTINCT r FROM Recipe r JOIN FETCH r.amounts a LEFT JOIN FETCH a.system LEFT JOIN FETCH a.ingredient",
			countQuery="SELECT count(r.id) FROM Recipe r")
	Page<Recipe> findAll(Pageable pageable);
}
