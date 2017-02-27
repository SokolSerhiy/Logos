package ua.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.dto.form.AmountForm;
import ua.service.AmountService;

public class AmountValidator implements Validator{

	private final static Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	
	private final AmountService amountService;
	
	public AmountValidator(AmountService amountService) {
		this.amountService = amountService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AmountForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AmountForm form = (AmountForm) target;
		if(!REG.matcher(form.getAmount()).matches()){
			errors.rejectValue("amount", "", "Enter number with . or , or only numbers");
		}
		if(errors.getFieldError("amount")==null){
			if(amountService.findUnique(form.getAmount(), form.getIngredient(), form.getSystem())!=null){
				errors.rejectValue("ingredient", "", "Already exist");
			}
		}
	}

}
