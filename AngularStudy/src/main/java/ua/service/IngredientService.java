package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Ingredient;
import ua.filter.BasicFilter;

public interface IngredientService{

	Ingredient findOne(Long id);
	
	Page<Ingredient> findAll(Pageable pageable, BasicFilter filter);
	
	Ingredient save(Ingredient entity);
	
	void delete(Long id);

	List<Ingredient> findAll();

}
