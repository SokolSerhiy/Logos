package ua.service;

import java.util.List;

import ua.entity.Category;

public interface CategoryService {

	List<Category> findAll();
	
	void delete(int id);

	void save(Category category);

	Category findOne(int id);

	void addNoss(int id, int nossId);

	void addNosd(int id, int nosdId);

	Category loadedNoss(int id);

	Category loadedNosd(int id);

	void deleteNoss(int id, int nossId);

	void deleteNosd(int id, int nosdId);

}
