package ua.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.RecipeForm;

public class RecipeValidator implements Validator{
	
	private static final Pattern p = Pattern.compile("^[0-9]+$");

	@Override
	public boolean supports(Class<?> clazz) {
		return RecipeForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RecipeForm form = (RecipeForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "", "Can't be empty");
		if(form.getCountry()==null){
			errors.rejectValue("country", "", "Select country");
		}
		if(form.getAmounts().isEmpty()){
			errors.rejectValue("amounts", "", "Select more then 0 amounts");
		}
		if(!p.matcher(form.getTime()).matches()){
			errors.rejectValue("time", "", "Enter only numbers");
		}
	}

}
