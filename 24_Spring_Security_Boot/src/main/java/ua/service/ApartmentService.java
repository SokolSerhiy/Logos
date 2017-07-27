package ua.service;

import java.util.List;

import ua.domain.request.ApartmentRequest;
import ua.domain.view.ApartmentIndex;
import ua.entity.Apartment;

public interface ApartmentService extends CrudService<Apartment, Integer>{

	List<ApartmentIndex> findAllIndex();

	void save(ApartmentRequest request);

	List<String> findRentTypeNames();

	List<String> findAreaNames();

	List<String> findStreetNames();
}