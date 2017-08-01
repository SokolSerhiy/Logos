package ua.service;

import java.util.List;

import ua.entity.Street;

public interface StreetService extends CrudService<Street, Integer>{

	List<String> findNames();

	Street findByName(String name);
}
