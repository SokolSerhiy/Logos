package ua.service;

import java.util.List;

import ua.entity.Amount;

public interface AmountService{

	Amount findOne(Long id);
	
	List<Amount> findAll();
	
	void save(Amount entity);
	
	void delete(Long id);
}
