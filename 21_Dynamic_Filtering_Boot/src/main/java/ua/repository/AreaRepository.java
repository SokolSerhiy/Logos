package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Area;

public interface AreaRepository extends JpaRepository<Area, Integer>, JpaSpecificationExecutor<Area>{

	@Query("SELECT a.name FROM Area a")
	List<String> findNames();

	Area findByName(String name);

}
