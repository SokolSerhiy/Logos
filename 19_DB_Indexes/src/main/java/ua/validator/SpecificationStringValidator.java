package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.SpecificationString;
import ua.service.SpecificationStringService;

public class SpecificationStringValidator implements Validator{

	private final SpecificationStringService stringService;

	public SpecificationStringValidator(SpecificationStringService stringService) {
		this.stringService = stringService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return SpecificationString.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SpecificationString specificationString = (SpecificationString) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(stringService.findOne(specificationString.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}
	
	
}
