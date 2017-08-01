package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.RentType;

public interface RentTypeRepository extends JpaRepository<RentType, Integer>{

//	@Query("SELECT rt FROM RentType rt WHERE rt.name = ?1")
	RentType findByName(String name);

	@Query("SELECT rt.name FROM RentType rt")
	List<String> findNames();
	
}
