package ua.service;

import java.util.List;

import ua.entity.MeasuringSystem;

public interface MeasuringSystemService {

	List<MeasuringSystem> findAll();

	void delete(int id);

	void save(MeasuringSystem form);

	MeasuringSystem findOne(int id);

}
