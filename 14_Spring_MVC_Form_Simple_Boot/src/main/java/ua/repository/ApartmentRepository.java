package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.domain.ApartmentIndex;
import ua.entity.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer>{

	@Query("SELECT a FROM Apartment a JOIN FETCH a.rentType JOIN FETCH a.street JOIN FETCH a.area")
	List<Apartment> findAll();
	
	@Query("SELECT a FROM Apartment a JOIN FETCH a.rentType JOIN FETCH a.area")
	List<Apartment> findAllRentTypeAndAreaLoaded();
	
	@Query("SELECT new ua.domain.ApartmentIndex(a.id, a.photoUrl, a.version, a.price, rt.name, ar.name, a.rate, a.rooms) FROM Apartment a JOIN a.rentType rt JOIN a.area ar")
	List<ApartmentIndex> findApartmentIndex();
	
}
