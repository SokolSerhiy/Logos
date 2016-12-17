package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.NameOfSpecificationDigital;
import ua.service.NameOfSpecificationDigitalService;

public class NameOfSpecificationDigitalValidator implements Validator{

	private final NameOfSpecificationDigitalService digitalService;

	public NameOfSpecificationDigitalValidator(NameOfSpecificationDigitalService digitalService) {
		this.digitalService = digitalService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NameOfSpecificationDigital.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NameOfSpecificationDigital nosd = (NameOfSpecificationDigital)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(digitalService.findOne(nosd.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}
	
	
}
