package ua.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.dto.AmountDto;
import ua.entity.Amount;
import ua.filter.AmountFilter;
import ua.filter.spacification.AmountSpecification;
import ua.repository.AmountRepository;
import ua.repository.IngredientRepository;
import ua.repository.MeasuringSystemRepository;
import ua.service.AmountService;

@Service
public class AmountServiceImpl implements AmountService{

	@Autowired
	private AmountRepository amountRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private MeasuringSystemRepository measuringSystemRepository;
	
	@Override
	public Amount findOne(Long id) {
		return amountRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		amountRepository.delete(id);
	}

	@Override
	public List<AmountDto> findAll() {
		return amountRepository.findAll().stream().map(this::map).collect(Collectors.toList());
	}

	@Override
	public void save(AmountDto amountDto) {
		Amount amount = new Amount();
		amount.setAmount(amountDto.getAmount());
		amount.setId(amountDto.getId());
		amount.setIngredient(ingredientRepository.findByName(amountDto.getIngredientName()));
		amount.setSystem(measuringSystemRepository.findByName(amountDto.getMeasureName()));
		amountRepository.save(amount);
	}

	@Override
	public Page<AmountDto> findAll(Pageable pageable, AmountFilter filter) {
		return amountRepository.findAll(new AmountSpecification(filter), pageable).map(this::map);
	}

	private AmountDto map(Amount amount){
		AmountDto dto = new AmountDto();
		dto.setAmount(amount.getAmount());
		dto.setId(amount.getId());
		dto.setIngredientName(amount.getIngredient().getName());
		dto.setMeasureName(amount.getSystem().getName());
		return dto;
	}

	@Override
	public void save(Amount amount) {
		amountRepository.saveAndFlush(amount);
	}
}