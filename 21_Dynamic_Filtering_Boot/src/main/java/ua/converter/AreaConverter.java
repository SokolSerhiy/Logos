package ua.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.Area;
import ua.repository.AreaRepository;

@Component
public class AreaConverter implements Converter<String, Area>{

	private final AreaRepository repository;
	
	@Autowired
	public AreaConverter(AreaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Area convert(String source) {
		return repository.findByName(source);
	}
}
