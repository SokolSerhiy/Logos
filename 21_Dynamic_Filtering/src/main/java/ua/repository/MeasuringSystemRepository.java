package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.MeasuringSystem;

public interface MeasuringSystemRepository extends JpaRepository<MeasuringSystem, Integer>, JpaSpecificationExecutor<MeasuringSystem>{

	MeasuringSystem findByName(String name);

}
