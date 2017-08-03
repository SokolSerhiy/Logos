package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.domain.view.ApartmentIndex;
import ua.entity.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer>{

	@Query("SELECT a FROM Apartment a JOIN FETCH a.rentType JOIN FETCH a.street JOIN FETCH a.area")
	List<Apartment> findAll();
	
	@Query("SELECT a FROM Apartment a JOIN FETCH a.rentType JOIN FETCH a.area")
	List<Apartment> findAllRentTypeAndAreaLoaded();
	
	@Query("SELECT new ua.domain.view.ApartmentIndex(a.id, a.photoUrl, a.version, a.price, rt.name, ar.name, a.rate, a.rooms) FROM Apartment a JOIN a.rentType rt JOIN a.area ar")
	Page<ApartmentIndex> findApartmentIndex(Pageable pageable);
	
	@Query("SELECT rt.name FROM RentType rt")
	List<String> findRentTypeNames();
	
	@Query("SELECT a.name FROM Area a")
	List<String> findAreaNames();

	@Query("SELECT s.name FROM Street s")
	List<String> findStreetNames();
	
	@Query("SELECT new ua.domain.view.ApartmentIndex(a.id, a.photoUrl, a.version, a.price, rt.name, ar.name, a.rate, a.rooms) FROM Apartment a JOIN a.rentType rt JOIN a.area ar ORDER BY a.rate DESC")
	List<ApartmentIndex> findTop5ByRate(Pageable pageable);
	
}
