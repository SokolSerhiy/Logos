package ua.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.SpecificationDigital;

public interface SpecificationDigitalRepository extends JpaRepository<SpecificationDigital, Integer>{
	
	@Query("SELECT sd FROM SpecificationDigital sd "
			+ "LEFT JOIN FETCH sd.measuringSystems WHERE "
			+ "sd.nameOfSpecificationDigital.id=:nosdId "
			+ "AND sd.value=:value")
	SpecificationDigital findByNosdValue(@Param("nosdId")Integer nosdId, @Param("value")BigDecimal value);

}
