package ua.service;

import java.util.List;

import ua.entity.NameOfSpecificationString;

public interface NameOfSpecificationStringService {

	List<NameOfSpecificationString> findAll();

	void delete(int id);

	void save(String name);

	List<NameOfSpecificationString> findByCategoryId(int id);

	NameOfSpecificationString findOne(int nossId);

}
