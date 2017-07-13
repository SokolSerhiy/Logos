package ua.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Street;
import ua.repository.StreetRepository;
import ua.service.StreetService;

@Service
public class StreetServiceImpl extends CrudServiceImpl<Street, Integer> implements StreetService{

	@Autowired
	public StreetServiceImpl(StreetRepository repository) {
		super(repository);
	}
}
