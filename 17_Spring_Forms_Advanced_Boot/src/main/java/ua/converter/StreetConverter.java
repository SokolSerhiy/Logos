package ua.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Street;
import ua.repository.StreetRepository;

@Component
public class StreetConverter implements Converter<String, Street>{

	private final StreetRepository repository;
	
	@Autowired
	public StreetConverter(StreetRepository repository) {
		this.repository = repository;
	}

	@Override
	public Street convert(String source) {
		return repository.findByName(source);
	}

}
