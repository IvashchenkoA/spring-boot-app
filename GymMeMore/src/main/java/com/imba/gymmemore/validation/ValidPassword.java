package com.imba.gymmemore.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordNotValid.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "The password should contain at least 1 uppercase letter," +
            " 1 lowercase letter and a digit 0-9";
    Class[] groups() default {};
    Class[] payload() default {};

}
