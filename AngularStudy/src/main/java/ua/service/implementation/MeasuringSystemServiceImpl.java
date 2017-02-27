package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.MeasuringSystem;
import ua.filter.BasicFilter;
import ua.filter.spacification.MeasuringSystemSpecification;
import ua.repository.MeasuringSystemRepository;
import ua.service.MeasuringSystemService;
@Service
public class MeasuringSystemServiceImpl implements MeasuringSystemService{

	@Autowired
	private MeasuringSystemRepository measuringSystemRepository;

	@Override
	public MeasuringSystem findOne(Long id) {
		return measuringSystemRepository.findOne(id);
	}

	@Override
	public Page<MeasuringSystem> findAll(Pageable pageable, BasicFilter filter) {
		return measuringSystemRepository.findAll(new MeasuringSystemSpecification(filter), pageable);
	}

	@Override
	public void save(MeasuringSystem entity) {
		measuringSystemRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		measuringSystemRepository.delete(id);
	}

	@Override
	public List<MeasuringSystem> findAll() {
		return measuringSystemRepository.findAll();
	}
}
