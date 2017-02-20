package ua.service;

import java.util.List;

import ua.entity.Ingredient;

public interface IngredientService{

	Ingredient findOne(Long id);
	
	List<Ingredient> findAll();
	
	void save(Ingredient entity);
	
	void delete(Long id);
}
