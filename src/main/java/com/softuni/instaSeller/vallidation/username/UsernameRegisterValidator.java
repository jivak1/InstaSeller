package com.softuni.instaSeller.vallidation.username;

import com.softuni.instaSeller.model.dto.UserRegisterFormDto;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.* ;

public class UsernameRegisterValidator implements ConstraintValidator<UsernameRegisterValidate, UserRegisterFormDto> {
    @Autowired
    private UserRepository userRepository ;

    @Override
    public void initialize(UsernameRegisterValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterFormDto userRegisterFormDto, ConstraintValidatorContext constraintValidatorContext) {
        UserEntity user = this.userRepository.findByUsername(userRegisterFormDto.getUsername()) ;
        if(user == null)
            return true ;
        else
            return false ;
    }
}
