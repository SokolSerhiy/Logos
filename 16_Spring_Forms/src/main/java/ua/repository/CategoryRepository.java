package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.nameOfSpecificationStrings WHERE "
			+ "c.id=:id")
	Category loadedNoss(@Param("id")int id);
	
	@Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.nameOfSpecificationDigitals WHERE "
			+ "c.id=:id")
	Category loadedNosd(@Param("id")int id);

}
