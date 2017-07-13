package ua.service;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, ID extends Serializable> {

	T findOne(ID id);
	
	List<T> findAll();
	
	void save(T entity);
	
	void delete(ID id);
}
