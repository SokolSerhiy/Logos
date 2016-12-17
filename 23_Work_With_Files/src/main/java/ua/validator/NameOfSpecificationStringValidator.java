package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.NameOfSpecificationString;
import ua.service.NameOfSpecificationStringService;

public class NameOfSpecificationStringValidator implements Validator{

	private final NameOfSpecificationStringService stringService;

	public NameOfSpecificationStringValidator(NameOfSpecificationStringService stringService) {
		this.stringService = stringService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NameOfSpecificationString.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NameOfSpecificationString noss = (NameOfSpecificationString)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(stringService.findOne(noss.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}
	
	
}
