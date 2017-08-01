package ua.service;

import java.util.List;

import ua.entity.RentType;

public interface RentTypeService extends CrudService<RentType, Integer>{

	List<String> findNames();
	
	RentType findByName(String name);
}
