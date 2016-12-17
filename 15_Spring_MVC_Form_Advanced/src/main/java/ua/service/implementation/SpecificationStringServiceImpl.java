package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.NameOfSpecificationString;
import ua.entity.SpecificationString;
import ua.repository.NameOfSpecificationStringRepository;
import ua.repository.SpecificationStringRepository;
import ua.service.SpecificationStringService;
@Service
public class SpecificationStringServiceImpl implements SpecificationStringService{

	@Autowired
	private SpecificationStringRepository specificationStringRepository;
	
	@Autowired
	private NameOfSpecificationStringRepository nameOfSpecificationStringRepository;
	
	@Override
	public List<SpecificationString> findAll() {
		return specificationStringRepository.findAll();
	}

	@Override
	public void delete(int id) {
		specificationStringRepository.delete(id);
	}

	@Override
	public void save(String name, int nossId) {
		NameOfSpecificationString nameOfSpecificationString = nameOfSpecificationStringRepository.findOne(nossId);
		SpecificationString entity = new SpecificationString();
		entity.setName(name);
		entity.setNameOfSpecificationString(nameOfSpecificationString);
		specificationStringRepository.save(entity);
	}

}
