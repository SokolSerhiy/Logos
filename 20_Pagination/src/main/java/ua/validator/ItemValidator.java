package ua.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.dto.form.ItemForm;

public class ItemValidator implements Validator{
	
	private final static Pattern PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})$");

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ItemForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ItemForm item = (ItemForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty");
		if(!PATTERN.matcher(item.getPrice()).matches()){
			errors.rejectValue("price", "", "Wrong format, only 2 digits after separator");
		}
		for(int i = 0; i < item.getSpecificationDigitals().size(); i++){
			if(!PATTERN.matcher(item.getSpecificationDigitals().get(i).getValue()).matches()){
				errors.rejectValue("specificationDigitals["+i+"].value", "", "Wrong format, only 2 digits after separator");
			}
		}
	}

}
