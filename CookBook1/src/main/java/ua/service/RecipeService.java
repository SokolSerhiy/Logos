package ua.service;

import java.util.List;

import ua.entity.Recipe;

public interface RecipeService{

	Recipe findOne(Long id);

	List<Recipe> findAll();

	void save(Recipe entity);

	void delete(Long id);

}
