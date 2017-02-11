package ua.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.dto.filter.BasicFilter;
import ua.dto.rest.NameOfSpecStringDto;
import ua.dto.rest.SpecStringDto;
import ua.entity.Category;
import ua.entity.NameOfSpecificationString;
import ua.entity.SpecificationString;
import ua.repository.NameOfSpecificationStringRepository;
import ua.service.NameOfSpecificationStringService;
import ua.service.specification.NameOfSpecificationStringExludeSpecification;
import ua.service.specification.NameOfSpecificationStringSpecification;
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

	@Override
	public Page<NameOfSpecificationString> findAll(BasicFilter filter, Pageable pageable) {
		return nameOfSpecificationStringRepository.findAll(new NameOfSpecificationStringSpecification(filter), pageable);
	}

	@Override
	public Page<NameOfSpecificationString> findAllExcludeLoaded(BasicFilter filter, Pageable pageable, Category category) {
		return nameOfSpecificationStringRepository.findAll(new NameOfSpecificationStringExludeSpecification(filter, category), pageable);
	}

	@Override
	public List<NameOfSpecificationString> findAllLoadedSS() {
		return nameOfSpecificationStringRepository.findAllLoadedSS();
	}

	@Override
	public List<NameOfSpecStringDto> findByCategoryIdDto(int id) {
		return findByCategoryId(id).stream().map(this::mapper).collect(Collectors.toList());
	}
	
	private NameOfSpecStringDto mapper(NameOfSpecificationString specificationString){
		NameOfSpecStringDto dto = new NameOfSpecStringDto();
		dto.setName(specificationString.getName());
		dto.setSpecStrings(specificationString.getSpecificationStrings().stream().map(this::mapper).collect(Collectors.toList()));
		return dto;
	}
	
	private SpecStringDto mapper(SpecificationString specificationString){
		SpecStringDto dto = new SpecStringDto();
		dto.setName(specificationString.getName());
		dto.setId(specificationString.getId());
		return dto;
	}
}