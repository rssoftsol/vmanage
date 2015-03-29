package com.imanage.validators.username;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = UserNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUserNameValid {
	String message() default "Invalid username";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
