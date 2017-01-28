package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Ingredient;
import ua.service.IngredientService;

public class IngredientEditor extends PropertyEditorSupport{

	private final IngredientService service;

	public IngredientEditor(IngredientService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Ingredient ingredient = service.findOne(Long.valueOf(text));
		setValue(ingredient);
	}
}