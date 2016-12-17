package ua.service;

import java.util.List;

import ua.entity.NameOfSpecificationDigital;

public interface NameOfSpecificationDigitalService {

	List<NameOfSpecificationDigital> findAll();

	void delete(int id);

	void save(NameOfSpecificationDigital form);

	List<NameOfSpecificationDigital> findByCategoryId(int id);

	NameOfSpecificationDigital findOne(int id);

}
