package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Ingredient;
import ua.repository.IngredientRepository;
import ua.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService{

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Override
	public Ingredient findOne(Long id) {
		return ingredientRepository.findOne(id);
	}

	@Override
	public List<Ingredient> findAll() {
		return ingredientRepository.findAll();
	}

	@Override
	public void save(Ingredient entity) {
		ingredientRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		ingredientRepository.delete(id);
	}

}
