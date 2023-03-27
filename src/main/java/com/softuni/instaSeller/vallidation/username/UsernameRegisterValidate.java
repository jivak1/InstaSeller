package com.softuni.instaSeller.vallidation.username;

import jakarta.validation.* ;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UsernameRegisterValidator.class)
public @interface UsernameRegisterValidate {
    String message() default "Username Already Exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
