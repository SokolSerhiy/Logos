package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.NameOfSpecificationDigital;

public interface NameOfSpecificationDigitalRepository extends JpaRepository<NameOfSpecificationDigital, Integer>, JpaSpecificationExecutor<NameOfSpecificationDigital>{

	@Query("SELECT nosd FROM Category c JOIN c.nameOfSpecificationDigitals nosd WHERE c.id=:id")
	List<NameOfSpecificationDigital> findByCategoryId(@Param("id")int id);

	NameOfSpecificationDigital findByName(String name);
	@Query("SELECT DISTINCT nosd FROM NameOfSpecificationDigital nosd LEFT JOIN FETCH nosd.specificationDigitals ")
	List<NameOfSpecificationDigital> findAllLoadedSD();

}
