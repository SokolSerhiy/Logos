package ua.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.repository.AreaRepository;
import ua.validation.annotation.UniqueArea;

@Component
public class UniqueAreaValidator implements ConstraintValidator<UniqueArea, String>{

	@Autowired
	private AreaRepository repository;
	
	@Override
	public void initialize(UniqueArea annotation) {
		
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return repository.findByName(name) == null;
	}

}
