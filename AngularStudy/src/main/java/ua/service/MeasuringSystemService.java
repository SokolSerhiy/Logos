package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.MeasuringSystem;
import ua.filter.BasicFilter;

public interface MeasuringSystemService {
	
	MeasuringSystem findOne(Long id);
	
	Page<MeasuringSystem> findAll(Pageable pageable, BasicFilter filter);
	
	void save(MeasuringSystem entity);
	
	void delete(Long id);

	List<MeasuringSystem> findAll();
}
