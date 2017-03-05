package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.dto.filter.BasicFilter;
import ua.entity.MeasuringSystem;
import ua.repository.MeasuringSystemRepository;
import ua.service.MeasuringSystemService;
import ua.service.specification.MeasuringSystemSpecification;
@Service
public class MeasuringSystemServiceImpl implements MeasuringSystemService{

	@Autowired
	private MeasuringSystemRepository measuringSystemRepository;
	
	@Override
	public List<MeasuringSystem> findAll() {
		return measuringSystemRepository.findAll();
	}

	@Override
	public void delete(int id) {
		measuringSystemRepository.delete(id);
	}

	@Override
	public void save(MeasuringSystem form) {
		measuringSystemRepository.save(form);
	}

	@Override
	public MeasuringSystem findOne(int id) {
		return measuringSystemRepository.findOne(id);
	}

	@Override
	public MeasuringSystem findOne(String name) {
		return measuringSystemRepository.findByName(name);
	}

	@Override
	public Page<MeasuringSystem> findAll(BasicFilter filter, Pageable pageable) {
		return measuringSystemRepository.findAll(new MeasuringSystemSpecification(filter), pageable);
	}

}
