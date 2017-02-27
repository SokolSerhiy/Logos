package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.Ingredient;
import ua.service.IngredientService;

public class IngredientValidator implements Validator {
	
	private final IngredientService ingredientService;
	
	public IngredientValidator(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Ingredient.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Ingredient ingredient = (Ingredient) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty");
		if(ingredientService.findByName(ingredient.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}
}