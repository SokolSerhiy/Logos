package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Category;
import ua.entity.NameOfSpecificationDigital;
import ua.entity.NameOfSpecificationString;
import ua.repository.CategoryRepository;
import ua.repository.NameOfSpecificationDigitalRepository;
import ua.repository.NameOfSpecificationStringRepository;
import ua.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NameOfSpecificationStringRepository nameOfSpecificationStringRepository;
	
	@Autowired
	private NameOfSpecificationDigitalRepository nameOfSpecificationDigitalRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void delete(int id) {
		categoryRepository.delete(id);
	}

	@Override
	public void save(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	@Override
	@Transactional
	public void addNoss(int id, int nossId) {
		Category category = categoryRepository.loadedNoss(id);
		NameOfSpecificationString nameOfSpecificationString = nameOfSpecificationStringRepository.findOne(nossId);
		category.getNameOfSpecificationStrings().add(nameOfSpecificationString);
		categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void addNosd(int id, int nosdId) {
		Category category = categoryRepository.loadedNosd(id);
		NameOfSpecificationDigital nameOfSpecificationDigital = nameOfSpecificationDigitalRepository.findOne(nosdId);
		category.getNameOfSpecificationDigitals().add(nameOfSpecificationDigital);
		categoryRepository.save(category);
	}

	@Override
	public Category loadedNoss(int id) {
		return categoryRepository.loadedNoss(id);
	}

	@Override
	public Category loadedNosd(int id) {
		return categoryRepository.loadedNosd(id);
	}

	@Override
	@Transactional
	public void deleteNoss(int id, int nossId) {
		Category category = categoryRepository.loadedNoss(id);
		category.getNameOfSpecificationStrings().removeIf(s->s.getId()==nossId);
		categoryRepository.save(category);
	}

	@Override
	@Transactional
	public void deleteNosd(int id, int nosdId) {
		Category category = categoryRepository.loadedNosd(id);
		category.getNameOfSpecificationDigitals().removeIf(s->s.getId()==nosdId);
		categoryRepository.save(category);
	}

	@Override
	public Category findOne(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}
}
