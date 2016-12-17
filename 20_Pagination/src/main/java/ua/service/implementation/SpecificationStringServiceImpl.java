package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public void save(SpecificationString form) {
		specificationStringRepository.save(form);
	}

	@Override
	public SpecificationString findOne(int id) {
		return specificationStringRepository.findOne(id);
	}

	@Override
	public SpecificationString findOne(String name) {
		return specificationStringRepository.findByName(name);
	}

	@Override
	public Page<SpecificationString> findAll(Pageable pageable) {
		return specificationStringRepository.findAll(pageable);
	}

}
