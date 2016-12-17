package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.NameOfSpecificationDigital;

public interface NameOfSpecificationDigitalRepository extends JpaRepository<NameOfSpecificationDigital, Integer>{

	@Query("SELECT nosd FROM Category c JOIN c.nameOfSpecificationDigitals nosd WHERE c.id=:id")
	List<NameOfSpecificationDigital> findByCategoryId(@Param("id")int id);

	NameOfSpecificationDigital findByName(String name);

}
