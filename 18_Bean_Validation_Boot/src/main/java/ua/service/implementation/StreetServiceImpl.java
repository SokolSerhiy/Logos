package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Street;
import ua.repository.StreetRepository;
import ua.service.StreetService;

@Service
public class StreetServiceImpl extends CrudServiceImpl<Street, Integer> implements StreetService{

	private final StreetRepository repository;
	
	@Autowired
	public StreetServiceImpl(StreetRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public List<String> findNames() {
		return repository.findNames();
	}

	@Override
	public Street findByName(String name) {
		return repository.findByName(name);
	}
}
