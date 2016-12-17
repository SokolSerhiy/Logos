package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.BasicFilter;
import ua.entity.SpecificationString;

public interface SpecificationStringService {

	List<SpecificationString> findAll();

	void delete(int id);

	void save(SpecificationString form);

	SpecificationString findOne(int id);
	
	SpecificationString findOne(String name);

	Page<SpecificationString> findAll(BasicFilter filter, Pageable pageable);
	
}
