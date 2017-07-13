package ua.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Area;
import ua.repository.AreaRepository;
import ua.service.AreaService;

@Service
public class AreaServiceImpl extends CrudServiceImpl<Area, Integer> implements AreaService{

	@Autowired
	public AreaServiceImpl(AreaRepository repository) {
		super(repository);
	}
}