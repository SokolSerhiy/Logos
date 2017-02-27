package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Ingredient;
import ua.filter.BasicFilter;
import ua.filter.spacification.IngredientSpecification;
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
	public Ingredient save(Ingredient entity) {
		return ingredientRepository.saveAndFlush(entity);
	}

	@Override
	public void delete(Long id) {
		ingredientRepository.delete(id);
	}

	@Override
	public Page<Ingredient> findAll(Pageable pageable, BasicFilter filter) {
		return ingredientRepository.findAll(new IngredientSpecification(filter), pageable);
	}

	@Override
	public List<Ingredient> findAll() {
		return ingredientRepository.findAll();
	}

}
