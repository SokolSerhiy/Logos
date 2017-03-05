package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{

	@Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.nameOfSpecificationStrings WHERE "
			+ "c.id=:id")
	Category loadedNoss(@Param("id")int id);
	
	@Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.nameOfSpecificationDigitals WHERE "
			+ "c.id=:id")
	Category loadedNosd(@Param("id")int id);

	Category findByName(String name);
	
	List<Category> findByParentIsNull();
	@Transactional
	@Modifying
	@Query(value="UPDATE category c INNER JOIN category cc on c.id=cc.parent_id SET c.haveChilds = true ", nativeQuery=true)
	void validHaveChilds();

}
