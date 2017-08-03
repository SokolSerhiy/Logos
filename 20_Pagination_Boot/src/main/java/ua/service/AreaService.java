package ua.service;

import java.util.List;

import ua.entity.Area;

public interface AreaService extends CrudService<Area, Integer>{

	List<String> findNames();

	Area findByName(String name);
}
