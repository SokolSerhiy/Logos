package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.MeasuringSystem;

public interface MeasuringSystemRepository extends JpaRepository<MeasuringSystem, Long>, JpaSpecificationExecutor<MeasuringSystem>{

	MeasuringSystem findByName(String measureName);

}
