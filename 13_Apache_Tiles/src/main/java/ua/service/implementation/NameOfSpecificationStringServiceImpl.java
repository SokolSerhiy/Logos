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

}
