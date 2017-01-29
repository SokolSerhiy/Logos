package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Recipe;
import ua.form.RecipeForm;
import ua.repository.RecipeRepository;
import ua.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public RecipeForm findOne(Long id) {
		Recipe entity = recipeRepository.findOne(id);
		RecipeForm form = new RecipeForm();
		form.setAmounts(entity.getAmounts());
		form.setCountry(entity.getCountry());
		form.setId(entity.getId());
		form.setTime(String.valueOf(entity.getTime()));
		form.setTitle(entity.getTitle());
		return form;
	}

	@Override
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}

	@Override
	public void save(RecipeForm form) {
		Recipe entity = new Recipe();
		entity.setAmounts(form.getAmounts());
		entity.setCountry(form.getCountry());
		entity.setId(form.getId());
		entity.setTime(Integer.valueOf(form.getTime()));
		entity.setTitle(form.getTitle());
		recipeRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		recipeRepository.delete(id);
	}

	@Override
	public Page<Recipe> findAll(Pageable pageable) {
		return recipeRepository.findAll(pageable);
	}
}