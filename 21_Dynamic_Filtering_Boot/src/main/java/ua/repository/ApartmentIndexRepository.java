package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import ua.domain.filter.ApartmentFilter;
import ua.domain.view.ApartmentIndex;
import ua.entity.Apartment;

public interface ApartmentIndexRepository {

	Page<ApartmentIndex> findAllApartmentIndex(ApartmentFilter filter, Pageable pageable);

}
