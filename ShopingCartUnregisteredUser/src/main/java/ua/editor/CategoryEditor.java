package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Category;
import ua.service.CategoryService;

public class CategoryEditor extends PropertyEditorSupport {

	private final CategoryService categoryService;

	public CategoryEditor(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Category category = categoryService.findOne(Integer.valueOf(text));
		setValue(category);
	}
}
