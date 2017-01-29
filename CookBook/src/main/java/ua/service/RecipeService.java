package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Recipe;
import ua.form.RecipeForm;

public interface RecipeService{

	Page<Recipe> findAll(Pageable pageable);

	RecipeForm findOne(Long id);

	List<Recipe> findAll();

	void save(RecipeForm entity);

	void delete(Long id);

}
