package ua.service;

import java.util.List;

public interface CrudService<E, ID>{

	E findOne(ID id);
	
	List<E> findAll();
	
	void save(E entity);
	
	void delete(ID id);
}
