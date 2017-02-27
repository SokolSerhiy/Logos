package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Recipe;
import ua.repository.RecipeRepository;
import ua.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public Recipe findOne(Long id) {
		return recipeRepository.findOne(id);
	}

	@Override
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}

	@Override
	public void save(Recipe entity) {
		recipeRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		recipeRepository.delete(id);
	}

}