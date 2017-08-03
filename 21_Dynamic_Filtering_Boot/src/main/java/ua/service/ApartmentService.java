package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.domain.request.ApartmentRequest;
import ua.domain.view.ApartmentIndex;
import ua.entity.Apartment;

public interface ApartmentService extends CrudService<Apartment, Integer>{

	Page<ApartmentIndex> findAllIndex(Pageable pageable);

	void save(ApartmentRequest request);

	List<String> findRentTypeNames();

	List<String> findAreaNames();

	List<String> findStreetNames();

	List<ApartmentIndex> findTop5ByRate();
}