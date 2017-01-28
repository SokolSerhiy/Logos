package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
