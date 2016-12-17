package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.BasicFilter;
import ua.entity.MeasuringSystem;

public interface MeasuringSystemService {

	List<MeasuringSystem> findAll();

	void delete(int id);

	void save(MeasuringSystem form);

	MeasuringSystem findOne(int id);
	
	MeasuringSystem findOne(String name);

	Page<MeasuringSystem> findAll(BasicFilter filter, Pageable pageable);

}
