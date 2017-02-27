package ua.service.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.dto.form.AmountForm;
import ua.entity.Amount;
import ua.entity.Ingredient;
import ua.entity.MeasuringSystem;
import ua.repository.AmountRepository;
import ua.service.AmountService;

@Service
public class AmountServiceImpl implements AmountService{

	@Autowired
	private AmountRepository amountRepository;
	
	@Override
	public AmountForm findForm(Long id) {
		AmountForm form = new AmountForm();
		Amount entity = amountRepository.findOne(id);
		form.setAmount(String.valueOf(entity.getAmount()));
		form.setId(entity.getId());
		form.setIngredient(entity.getIngredient());
		form.setSystem(entity.getSystem());
		return form;
	}

	@Override
	public List<Amount> findAll() {
		return amountRepository.findAll();
	}

	@Override
	public void save(AmountForm form) {
		Amount entity = new Amount();
		entity.setAmount(new BigDecimal(form.getAmount().replace(',', '.')));
		entity.setId(form.getId());
		entity.setIngredient(form.getIngredient());
		entity.setSystem(form.getSystem());
		amountRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		amountRepository.delete(id);
	}

	@Override
	public Amount findOne(Long id) {
		return amountRepository.findOne(id);
	}

	@Override
	public Amount findUnique(String amount, Ingredient ingredient,
			MeasuringSystem system) {
		return amountRepository.findUnique(new BigDecimal(amount.replace(',', '.')), ingredient.getId(), system.getId());
	}

}