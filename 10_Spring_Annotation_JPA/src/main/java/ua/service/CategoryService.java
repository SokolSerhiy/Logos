package ua.service;

import java.util.List;

import ua.entity.Category;

public interface CategoryService {

	Category findOne(int id);
	
	List<Category> findAll();
	
	void save(Category category);
	
	void delete(int id);
	
}
