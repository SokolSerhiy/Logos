package ua.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import ua.entity.RentType;
import ua.repository.RentTypeRepository;

//@Component
public class RentTypeFormatter implements Formatter<RentType>{

	private final RentTypeRepository repository;
	
	@Autowired
	public RentTypeFormatter(RentTypeRepository repository) {
		this.repository = repository;
	}

	@Override
	public String print(RentType object, Locale locale) {
		return object.getName();
	}

	@Override
	public RentType parse(String text, Locale locale) throws ParseException {
		return repository.findByName(text);
	}

}
