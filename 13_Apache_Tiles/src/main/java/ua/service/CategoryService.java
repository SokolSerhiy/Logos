package ua.service;

import java.util.List;

import ua.entity.Category;

public interface CategoryService {

	List<Category> findAll();
	
	void delete(int id);
	
	Category findOne(int id);
	
	void save(Category category);

}
