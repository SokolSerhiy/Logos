package ua.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import ua.entity.Street;
import ua.repository.StreetRepository;

//@Component
public class StreetFormatter implements Formatter<Street>{

	private final StreetRepository repository;
	
	@Autowired
	public StreetFormatter(StreetRepository repository) {
		this.repository = repository;
	}

	@Override
	public String print(Street object, Locale locale) {
		return object.getName();
	}

	@Override
	public Street parse(String text, Locale locale) throws ParseException {
		return repository.findByName(text);
	}

}
