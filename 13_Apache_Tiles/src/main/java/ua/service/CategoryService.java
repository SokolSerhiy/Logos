package ua.service;

import java.util.List;

import ua.entity.Category;

public interface CategoryService {

	List<Category> findAll();
	
	void delete(int id);

}
