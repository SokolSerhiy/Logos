package ua.service;

import java.util.List;

import ua.domain.ApartmentIndex;
import ua.entity.Apartment;

public interface ApartmentService extends CrudService<Apartment, Integer>{

	List<ApartmentIndex> findAllIndex();
}