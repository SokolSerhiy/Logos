package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.SpecificationString;

public interface SpecificationStringRepository extends JpaRepository<SpecificationString, Integer>, JpaSpecificationExecutor<SpecificationString>{
	@Query("SELECT ss FROM SpecificationString ss LEFT JOIN FETCH ss.nameOfSpecificationString WHERE ss.id=:id")
	SpecificationString findOne(@Param("id")int id);

	SpecificationString findByName(String name);
}
