package ua.service.implementation;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.BasicFilter;
import ua.entity.Category;
import ua.entity.MeasuringSystem;
import ua.entity.NameOfSpecificationDigital;
import ua.entity.SpecificationDigital;
import ua.repository.CategoryRepository;
import ua.repository.NameOfSpecificationDigitalRepository;
import ua.service.NameOfSpecificationDigitalService;
import ua.service.specification.NameOfSpecificationDigitalExludeSpecification;
import ua.service.specification.NameOfSpecificationDigitalSpecification;
@Service
public class NameOfSpecificationDigitalServiceImpl implements  NameOfSpecificationDigitalService{

	@Autowired
	private NameOfSpecificationDigitalRepository nameOfSpecificationDigitalRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<NameOfSpecificationDigital> findAll() {
		return nameOfSpecificationDigitalRepository.findAll();
	}

	@Override
	public void delete(int id) {
		nameOfSpecificationDigitalRepository.delete(id);
	}

	@Override
	public void save(NameOfSpecificationDigital form) {
		nameOfSpecificationDigitalRepository.save(form);
	}

	@Override
	public List<NameOfSpecificationDigital> findByCategoryId(int id) {
		return nameOfSpecificationDigitalRepository.findByCategoryId(id);
	}

	@Override
	public NameOfSpecificationDigital findOne(int id) {
		return nameOfSpecificationDigitalRepository.findOne(id);
	}

	@Override
	public NameOfSpecificationDigital findOne(String name) {
		return nameOfSpecificationDigitalRepository.findByName(name);
	}

	@Override
	public Page<NameOfSpecificationDigital> findAll(BasicFilter filter, Pageable pageable) {
		return nameOfSpecificationDigitalRepository.findAll(new NameOfSpecificationDigitalSpecification(filter), pageable);
	}

	@Override
	public Page<NameOfSpecificationDigital> findAllExcludeLoaded(BasicFilter filter, Pageable pageable, Category category) {
		return nameOfSpecificationDigitalRepository.findAll(new NameOfSpecificationDigitalExludeSpecification(filter, category), pageable);
	}

	@Override
	@Transactional
	public List<NameOfSpecificationDigital> findAllLoadedSD() {
		List<NameOfSpecificationDigital> list = nameOfSpecificationDigitalRepository.findAllLoadedSD();
		for (NameOfSpecificationDigital nameOfSpecificationDigital : list) {
			for (SpecificationDigital specificationDigital : nameOfSpecificationDigital.getSpecificationDigitals()) {
				Hibernate.initialize(specificationDigital.getMeasuringSystems());
				for(MeasuringSystem ms : specificationDigital.getMeasuringSystems()){
					if(!nameOfSpecificationDigital.getMs().contains(ms)) nameOfSpecificationDigital.getMs().add(ms);
				}
			}
			
		}
		return list;
	}
}