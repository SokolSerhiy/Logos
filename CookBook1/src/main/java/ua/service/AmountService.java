package ua.service;

import java.util.List;

import ua.dto.form.AmountForm;
import ua.entity.Amount;
import ua.entity.Ingredient;
import ua.entity.MeasuringSystem;

public interface AmountService{

	Amount findOne(Long id);
	
	List<Amount> findAll();
	
	void save(AmountForm entity);
	
	void delete(Long id);

	AmountForm findForm(Long id);

	Amount findUnique(String amount, Ingredient ingredient,
			MeasuringSystem system);
}
