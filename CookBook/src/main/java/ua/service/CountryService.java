package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Country;

public interface CountryService extends CrudService<Country, Long>{

	Page<Country> findAll(Pageable pageable);

}
