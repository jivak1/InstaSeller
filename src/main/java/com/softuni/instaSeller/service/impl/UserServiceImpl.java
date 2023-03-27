package com.softuni.instaSeller.service.impl;

import com.softuni.instaSeller.model.dto.UserRegisterFormDto;
import com.softuni.instaSeller.model.entity.AuthorityEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.model.enums.Authority;
import com.softuni.instaSeller.repository.AuthorityRepository;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private AuthorityRepository authorityRepository ;
    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public UserEntity getLoggeedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
        UserEntity user = this.userRepository.findByUsername(authentication.getName()) ;

        return user ;
    }

    @Override
    public void addUser(UserRegisterFormDto userRegisterFormDto) {
        UserEntity user = this.modelMapper.map(userRegisterFormDto, UserEntity.class) ;
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        AuthorityEntity authority = this.authorityRepository.findByAuthority(Authority.valueOf(userRegisterFormDto.getAuthorityName())) ;

        authority.getUsers().add(user) ;

        user.setAuthority(authority);

        this.userRepository.save(user) ;
        this.authorityRepository.save(authority) ;
    }
}
