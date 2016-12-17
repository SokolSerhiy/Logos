package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.entity.MeasuringSystem;
import ua.service.MeasuringSystemService;

public class MeasuringSystemValidator implements Validator{

	private final MeasuringSystemService measuringSystemService;

	public MeasuringSystemValidator(MeasuringSystemService measuringSystemService) {
		this.measuringSystemService = measuringSystemService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(MeasuringSystem.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MeasuringSystem ms = (MeasuringSystem) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(measuringSystemService.findOne(ms.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}
	
	
}
