package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Ingredient;
import ua.service.IngredientService;

public class IngredientEditor extends PropertyEditorSupport{

	private final IngredientService ingredientService;

	public IngredientEditor(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Ingredient ingredient = ingredientService.findOne(Long.valueOf(text));
		setValue(ingredient);
	}
	
	
}
