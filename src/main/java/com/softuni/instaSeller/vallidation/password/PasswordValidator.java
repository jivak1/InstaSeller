package com.softuni.instaSeller.vallidation.password;

import com.softuni.instaSeller.model.dto.UserRegisterFormDto;

import jakarta.validation.* ;

public class PasswordValidator implements ConstraintValidator<PasswordValidate, UserRegisterFormDto> {

    @Override
    public void initialize(PasswordValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterFormDto userRegisterFormDto, ConstraintValidatorContext constraintValidatorContext) {
        if(userRegisterFormDto.getPassword().equals(userRegisterFormDto.getConfirmPassword()))
            return true ;
        else
            return false ;
    }
}
