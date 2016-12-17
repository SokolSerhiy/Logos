package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Producer;
import ua.repository.ProducerRepository;
import ua.service.ProducerService;
@Service
public class ProducerServiceImpl implements ProducerService{

	@Autowired
	private ProducerRepository producerRepository;
	
	@Override
	public List<Producer> findAll() {
		return producerRepository.findAll();
	}

	@Override
	public void delete(int id) {
		producerRepository.delete(id);
	}

	@Override
	public void save(Producer form) {
		producerRepository.save(form);
	}

	@Override
	public Producer findOne(int id) {
		return producerRepository.findOne(id);
	}

	@Override
	public Producer findOne(String name) {
		return producerRepository.findByName(name);
	}

	@Override
	public Page<Producer> findAll(Pageable pageable) {
		return producerRepository.findAll(pageable);
	}
}
