package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.SimpleFilter;
import ua.entity.Ingredient;

public interface IngredientService{

	Ingredient findOne(Long id);
	
	List<Ingredient> findAll();
	
	void save(Ingredient entity);
	
	void delete(Long id);

	Ingredient findByName(String name);

	Page<Ingredient> findAll(Pageable pageable, SimpleFilter filter);
}
