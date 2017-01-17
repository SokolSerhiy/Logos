package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Category;
import ua.repository.CategoryRepository;
import ua.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository repository;

	@Override
	@Transactional(readOnly=true)
	public Category findOne(int id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Category> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(Category category) {
		repository.save(category);
	}

	@Override
	public void delete(int id) {
		repository.delete(id);
	}
	
}
