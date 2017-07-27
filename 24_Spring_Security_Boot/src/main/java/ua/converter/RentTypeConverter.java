package ua.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.RentType;
import ua.repository.RentTypeRepository;

@Component
public class RentTypeConverter implements Converter<String, RentType>{

	private final RentTypeRepository repository;
	
	@Autowired
	public RentTypeConverter(RentTypeRepository repository) {
		this.repository = repository;
	}

	@Override
	public RentType convert(String source) {
		return repository.findByName(source);
	}
}
