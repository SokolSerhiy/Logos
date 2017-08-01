package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.RentType;
import ua.repository.RentTypeRepository;
import ua.service.RentTypeService;

@Service
public class RentTypeServiceImpl extends CrudServiceImpl<RentType, Integer> implements RentTypeService{

	private final RentTypeRepository repository;
	
	@Autowired
	public RentTypeServiceImpl(RentTypeRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public List<String> findNames() {
		return repository.findNames();
	}

	@Override
	public RentType findByName(String name) {
		return repository.findByName(name);
	}

}
