package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer>{

	@Query("SELECT a FROM Apartment a JOIN FETCH a.rentType JOIN FETCH a.street JOIN FETCH a.area")
	List<Apartment> findAll();
}
