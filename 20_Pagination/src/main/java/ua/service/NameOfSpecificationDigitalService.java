package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.NameOfSpecificationDigital;

public interface NameOfSpecificationDigitalService {

	List<NameOfSpecificationDigital> findAll();

	void delete(int id);

	void save(NameOfSpecificationDigital form);

	List<NameOfSpecificationDigital> findByCategoryId(int id);

	NameOfSpecificationDigital findOne(int id);

	NameOfSpecificationDigital findOne(String name);

	Page<NameOfSpecificationDigital> findAll(Pageable pageable);
}
