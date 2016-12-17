package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.SpecificationString;
import ua.repository.SpecificationStringRepository;
import ua.service.SpecificationStringService;
@Service
public class SpecificationStringServiceImpl implements SpecificationStringService{

	@Autowired
	private SpecificationStringRepository specificationStringRepository;
	
	@Override
	public List<SpecificationString> findAll() {
		return specificationStringRepository.findAll();
	}

	@Override
	public void delete(int id) {
		specificationStringRepository.delete(id);
	}

}
