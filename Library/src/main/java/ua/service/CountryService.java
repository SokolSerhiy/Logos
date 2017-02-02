package ua.service;

import java.util.List;

import ua.entity.Country;

public interface CountryService {

	Country findOne(int id);

	List<Country> findAll();

	void delete(int id);

	void save(Country entity);
}
