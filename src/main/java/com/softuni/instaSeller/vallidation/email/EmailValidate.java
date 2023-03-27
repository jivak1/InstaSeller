package com.softuni.instaSeller.vallidation.email;

import jakarta.validation.* ;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValidate {
    String message() default "Email Already Exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
