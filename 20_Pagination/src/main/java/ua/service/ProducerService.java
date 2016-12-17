package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Producer;

public interface ProducerService {

	List<Producer> findAll();

	void delete(int id);

	void save(Producer form);

	Producer findOne(int id);
	
	Producer findOne(String name);

	Page<Producer> findAll(Pageable pageable);
	
}
