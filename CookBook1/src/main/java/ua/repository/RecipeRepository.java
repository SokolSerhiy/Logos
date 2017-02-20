package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{
}
