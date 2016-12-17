package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.NameOfSpecificationString;

public interface NameOfSpecificationStringService {

	List<NameOfSpecificationString> findAll();

	void delete(int id);

	void save(NameOfSpecificationString form);

	List<NameOfSpecificationString> findByCategoryId(int id);

	NameOfSpecificationString findOne(int id);
	
	NameOfSpecificationString findOne(String name);

	Page<NameOfSpecificationString> findAll(Pageable pageable);

}
