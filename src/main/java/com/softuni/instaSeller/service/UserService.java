package com.softuni.instaSeller.service;

import com.softuni.instaSeller.model.dto.UserRegisterFormDto;
import com.softuni.instaSeller.model.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    public UserEntity getLoggeedUser() ;

    public void addUser(UserRegisterFormDto userRegisterFormDto) ;
}
