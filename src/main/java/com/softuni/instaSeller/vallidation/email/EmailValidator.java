package com.softuni.instaSeller.vallidation.email;

import com.softuni.instaSeller.model.dto.UserRegisterFormDto;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.* ;

public class EmailValidator implements ConstraintValidator<EmailValidate, UserRegisterFormDto> {
    @Autowired
    private UserRepository userRepository ;

    @Override
    public void initialize(EmailValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterFormDto userRegisterFormDto, ConstraintValidatorContext constraintValidatorContext) {
        UserEntity user = this.userRepository.findByEmail(userRegisterFormDto.getEmail()) ;
        if(user == null)
            return true ;
        else
            return false ;
    }
}
