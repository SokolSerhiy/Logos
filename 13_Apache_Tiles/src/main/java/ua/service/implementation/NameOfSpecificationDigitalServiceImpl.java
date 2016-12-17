package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.NameOfSpecificationDigital;
import ua.repository.NameOfSpecificationDigitalRepository;
import ua.service.NameOfSpecificationDigitalService;
@Service
public class NameOfSpecificationDigitalServiceImpl implements  NameOfSpecificationDigitalService{

	@Autowired
	private NameOfSpecificationDigitalRepository nameOfSpecificationDigitalRepository;
	
	@Override
	public List<NameOfSpecificationDigital> findAll() {
		return nameOfSpecificationDigitalRepository.findAll();
	}

	@Override
	public void delete(int id) {
		nameOfSpecificationDigitalRepository.delete(id);
	}

}
