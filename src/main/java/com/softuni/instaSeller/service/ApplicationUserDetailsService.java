package com.softuni.instaSeller.service;

import com.softuni.instaSeller.model.entity.AuthorityEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username) ;
        if(userEntity == null)
            throw new UsernameNotFoundException("User with name " + username + " not found!");
        UserDetails userDetails = map(userEntity) ;
        return userDetails ;
    }

    private UserDetails map(UserEntity userEntity) {
        return new User(userEntity.getUsername(), userEntity.getPassword(), extractAuthorities(userEntity)) ;
    }

    private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {

        return mapRole(userEntity.getAuthority()) ;
    }

    private List<GrantedAuthority> mapRole(AuthorityEntity authorityEntity) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>() ;
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + authorityEntity.getAuthorityName())) ;
        return grantedAuthorities;
    }
}
