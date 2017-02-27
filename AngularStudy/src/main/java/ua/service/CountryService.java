package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Country;
import ua.filter.BasicFilter;

public interface CountryService{

	Country findOne(Long id);
	
	Page<Country> findAll(Pageable pageable, BasicFilter filter);
	
	void save(Country entity);
	
	void delete(Long id);

	List<Country> findAll();
}
