package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.dto.filter.BasicFilter;
import ua.entity.Category;
import ua.entity.NameOfSpecificationString;
import ua.repository.NameOfSpecificationStringRepository;
import ua.service.NameOfSpecificationStringService;
import ua.service.specification.NameOfSpecificationStringExludeSpecification;
import ua.service.specification.NameOfSpecificationStringSpecification;
@Service
public class NameOfSpecificationStringServiceImpl implements NameOfSpecificationStringService{

	@Autowired
	private NameOfSpecificationStringRepository nameOfSpecificationStringRepository;
	
	@Override
	public List<NameOfSpecificationString> findAll() {
		return nameOfSpecificationStringRepository.findAll();
	}

	@Override
	public void delete(int id) {
		nameOfSpecificationStringRepository.delete(id);
	}
	
	@Override
	public void save(NameOfSpecificationString form) {
		nameOfSpecificationStringRepository.save(form);
	}

	@Override
	public List<NameOfSpecificationString> findByCategoryId(int id) {
		return nameOfSpecificationStringRepository.findByCategoryId(id);
	}

	@Override
	public NameOfSpecificationString findOne(int id) {
		return nameOfSpecificationStringRepository.findOne(id);
	}

	@Override
	public NameOfSpecificationString findOne(String name) {
		return nameOfSpecificationStringRepository.findByName(name);
	}

	@Override
	public Page<NameOfSpecificationString> findAll(BasicFilter filter, Pageable pageable) {
		return nameOfSpecificationStringRepository.findAll(new NameOfSpecificationStringSpecification(filter), pageable);
	}

	@Override
	public Page<NameOfSpecificationString> findAllExcludeLoaded(BasicFilter filter, Pageable pageable, Category category) {
		return nameOfSpecificationStringRepository.findAll(new NameOfSpecificationStringExludeSpecification(filter, category), pageable);
	}

	@Override
	public List<NameOfSpecificationString> findAllLoadedSS() {
		return nameOfSpecificationStringRepository.findAllLoadedSS();
	}
}