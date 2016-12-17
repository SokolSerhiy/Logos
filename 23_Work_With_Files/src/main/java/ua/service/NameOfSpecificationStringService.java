package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.BasicFilter;
import ua.entity.Category;
import ua.entity.NameOfSpecificationString;

public interface NameOfSpecificationStringService {

	List<NameOfSpecificationString> findAll();
	
	List<NameOfSpecificationString> findAllLoadedSS();

	void delete(int id);

	void save(NameOfSpecificationString form);

	List<NameOfSpecificationString> findByCategoryId(int id);

	NameOfSpecificationString findOne(int id);
	
	NameOfSpecificationString findOne(String name);

	Page<NameOfSpecificationString> findAll(BasicFilter filter, Pageable pageable);

	Page<NameOfSpecificationString> findAllExcludeLoaded(BasicFilter filter, Pageable pageable, Category category);
}
