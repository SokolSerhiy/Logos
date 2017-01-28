package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
	@Query("SELECT DISTINCT r FROM Recipe r JOIN FETCH r.amounts a LEFT JOIN FETCH a.system LEFT JOIN FETCH a.ingredient")
	List<Recipe> findAll();
}
