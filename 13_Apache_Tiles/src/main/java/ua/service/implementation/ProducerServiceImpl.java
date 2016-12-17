package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
