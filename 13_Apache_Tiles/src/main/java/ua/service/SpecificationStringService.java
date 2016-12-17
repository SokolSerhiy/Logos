package ua.service;

import java.util.List;

import ua.entity.SpecificationString;

public interface SpecificationStringService {

	List<SpecificationString> findAll();

	void delete(int id);
	
}
