package ua.validation.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ua.validation.validator.UniqueRentTypeValidator;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy=UniqueRentTypeValidator.class)
public @interface UniqueRentType {

	String message() default "Not unique";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
