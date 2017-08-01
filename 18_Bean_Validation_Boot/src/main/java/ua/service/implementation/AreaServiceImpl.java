package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Area;
import ua.repository.AreaRepository;
import ua.service.AreaService;

@Service
public class AreaServiceImpl extends CrudServiceImpl<Area, Integer> implements AreaService{

	private final AreaRepository repository;
	
	@Autowired
	public AreaServiceImpl(AreaRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public List<String> findNames() {
		return repository.findNames();
	}

	@Override
	public Area findByName(String name) {
		return repository.findByName(name);
	}
}