package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.form.AmountForm;
import ua.entity.Amount;
import ua.entity.Ingredient;
import ua.entity.MeasuringSystem;

public interface AmountService{

	Amount findOne(Long id);
	
	Page<Amount> findAll(Pageable pageable);
	
	void save(AmountForm entity);
	
	void delete(Long id);

	AmountForm findForm(Long id);

	Amount findUnique(String amount, Ingredient ingredient,
			MeasuringSystem system);

	List<Amount> findAll();
}
