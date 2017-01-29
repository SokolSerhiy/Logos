package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.MeasuringSystem;

public interface MeasuringSystemService extends CrudService<MeasuringSystem, Long>{

	Page<MeasuringSystem> findAll(Pageable pageable);

}
