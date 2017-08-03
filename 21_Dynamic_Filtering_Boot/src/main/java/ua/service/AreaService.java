package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.domain.filter.SimpleFilter;
import ua.entity.Area;

public interface AreaService extends CrudService<Area, Integer>{

	List<String> findNames();

	Area findByName(String name);

	Page<Area> findAll(Pageable pageable, SimpleFilter filter);
}
