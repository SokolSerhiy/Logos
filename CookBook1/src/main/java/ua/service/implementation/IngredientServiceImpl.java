package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.dto.filter.SimpleFilter;
import ua.entity.Ingredient;
import ua.repository.IngredientRepository;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.IngredientService;
import ua.specification.IngredientSpecification;

@Service
public class IngredientServiceImpl implements IngredientService{

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private FileWriter fileWriter;
	
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
		MultipartFile file = entity.getFile();
		entity = ingredientRepository.saveAndFlush(entity);
		if(fileWriter.write(Folder.INGREDIENT, file, entity.getId())){
			entity.setVersion(entity.getVersion()+1);
			ingredientRepository.save(entity);
		}
	}

	@Override
	public void delete(Long id) {
		ingredientRepository.delete(id);
	}

	@Override
	public Ingredient findByName(String name) {
		return ingredientRepository.findByName(name);
	}

	@Override
	public Page<Ingredient> findAll(Pageable pageable, SimpleFilter filter) {
		return ingredientRepository.findAll(new IngredientSpecification(filter), pageable);
	}

}
