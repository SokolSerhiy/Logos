package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Amount;
import ua.repository.AmountRepository;
import ua.service.AmountService;

@Service
public class AmountServiceImpl implements AmountService{

	@Autowired
	private AmountRepository amountRepository;
	
	@Override
	public Amount findOne(Long id) {
		return amountRepository.findOne(id);
	}

	@Override
	public List<Amount> findAll() {
		return amountRepository.findAll();
	}

	@Override
	public void save(Amount entity) {
		amountRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		amountRepository.delete(id);
	}
}