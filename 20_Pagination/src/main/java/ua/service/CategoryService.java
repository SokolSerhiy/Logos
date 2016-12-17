package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Category;

public interface CategoryService {

	List<Category> findAll();
	
	void delete(int id);

	void save(Category category);

	Category findOne(int id);
	
	Category findOne(String name);

	void addNoss(int id, int nossId);

	void addNosd(int id, int nosdId);

	Category loadedNoss(int id);

	Category loadedNosd(int id);

	void deleteNoss(int id, int nossId);

	void deleteNosd(int id, int nosdId);

	Page<Category> findAll(Pageable pageable);

}
