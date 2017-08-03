package ua.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import ua.entity.Area;
import ua.repository.AreaRepository;

//@Component
public class AreaFormatter implements Formatter<Area>{

	private final AreaRepository repository;
	
	@Autowired
	public AreaFormatter(AreaRepository repository){
		this.repository = repository;
	}
	
	@Override
	public String print(Area object, Locale locale) {
		return object.getName();
	}

	@Override
	public Area parse(String text, Locale locale) throws ParseException {
		return repository.findByName(text);
	}

}
