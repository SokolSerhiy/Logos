package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.People;
import ua.repository.PeopleRepository;
import ua.service.PeopleService;

@Service
public class PeopleServiceImpl implements PeopleService{

	@Autowired
	private PeopleRepository repository;

	@Override
	public List<People> findAll() {
		return repository.findAll();
	}

	@Override
	public People save(People people) {
		return repository.saveAndFlush(people);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public People findOne(Long id) {
		return repository.findOne(id);
	}
	
	
}
