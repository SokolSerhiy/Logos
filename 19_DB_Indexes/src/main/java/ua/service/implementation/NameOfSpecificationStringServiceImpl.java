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
	public void save(NameOfSpecificationString form) {
		nameOfSpecificationStringRepository.save(form);
	}

	@Override
	public List<NameOfSpecificationString> findByCategoryId(int id) {
		return nameOfSpecificationStringRepository.findByCategoryId(id);
	}

	@Override
	public NameOfSpecificationString findOne(int id) {
		return nameOfSpecificationStringRepository.findOne(id);
	}

	@Override
	public NameOfSpecificationString findOne(String name) {
		return nameOfSpecificationStringRepository.findByName(name);
	}
}