package ua.service;

import java.util.List;

import ua.entity.MeasuringSystem;

public interface MeasuringSystemService {
	
	MeasuringSystem findOne(Long id);
	
	List<MeasuringSystem> findAll();
	
	void save(MeasuringSystem entity);
	
	void delete(Long id);
}
