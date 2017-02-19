package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.NameOfSpecificationString;
import ua.repository.NameOfSpecificationStringRepository;
import ua.service.NameOfSpecificationStringService;
@Service
public class NameOfSpecificationStringServiceImpl implements NameOfSpecificationStringService{

	@Autowired
	private NameOfSpecificationStringRepository nameOfSpecificationStringRepository;
	
	@Override
	public List<NameOfSpecificationString> findAll() {
		return nameOfSpecificationStringRepository.findAll();
	}

	@Override
	public void delete(int id) {
		nameOfSpecificationStringRepository.delete(id);
	}
	
	@Override
	public void save(String name) {
		NameOfSpecificationString entity = new NameOfSpecificationString();
		entity.setName(name);
		nameOfSpecificationStringRepository.save(entity);
	}

	@Override
	public List<NameOfSpecificationString> findByCategoryId(int id) {
		return nameOfSpecificationStringRepository.findByCategoryId(id);
	}

	@Override
	public NameOfSpecificationString findOne(int nossId) {
		return nameOfSpecificationStringRepository.findOne(nossId);
	}
}