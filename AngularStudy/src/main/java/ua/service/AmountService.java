package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.AmountDto;
import ua.entity.Amount;
import ua.filter.AmountFilter;

public interface AmountService{

	Amount findOne(Long id);
	
	List<AmountDto> findAll();
	
	void save(AmountDto amountDto);
	
	void delete(Long id);

	Page<AmountDto> findAll(Pageable pageable, AmountFilter filter);

	void save(Amount amount);
}
