package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country>{

}
