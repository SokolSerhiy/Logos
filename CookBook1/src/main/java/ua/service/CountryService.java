package ua.service;

import java.util.List;

import ua.entity.Country;

public interface CountryService{

	Country findOne(Long id);
	
	List<Country> findAll();
	
	void save(Country entity);
	
	void delete(Long id);
}
