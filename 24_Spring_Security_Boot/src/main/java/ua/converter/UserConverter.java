package ua.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ua.entity.UserEntity;
import ua.repository.UserEntityRepository;

@Component
public class UserConverter implements Converter<String, UserEntity>{

	private final UserEntityRepository repository;
	
	public UserConverter(UserEntityRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserEntity convert(String source) {
		return repository.findByEmail(source);
	}

}
