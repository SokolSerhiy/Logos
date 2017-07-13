package ua.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.RentType;
import ua.repository.RentTypeRepository;
import ua.service.RentTypeService;

@Service
public class RentTypeServiceImpl extends CrudServiceImpl<RentType, Integer> implements RentTypeService{

	@Autowired
	public RentTypeServiceImpl(RentTypeRepository repository) {
		super(repository);
	}

}
