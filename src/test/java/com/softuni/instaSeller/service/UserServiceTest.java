package com.softuni.instaSeller.service;

import com.softuni.instaSeller.model.dto.UserRegisterFormDto;
import com.softuni.instaSeller.model.entity.AuthorityEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.model.enums.Authority;
import com.softuni.instaSeller.repository.AuthorityRepository;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository ;

    @Mock
    private AuthorityRepository authorityRepository ;

    @Mock
    private ModelMapper modelMapper ;

    @Mock
    private PasswordEncoder passwordEncoder ;

    @InjectMocks
    private UserServiceImpl userServiceToTest ;


    @Test
    void test_AddUser_Saves_User_And_Authority(){
        UserRegisterFormDto userRegisterFormDto = new UserRegisterFormDto() ;
        userRegisterFormDto.setUsername("testuser");
        userRegisterFormDto.setPassword("testpassword");
        userRegisterFormDto.setEmail("testemail@example.com");
        userRegisterFormDto.setAuthorityName("USER");

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(Authority.USER);
        authorityEntity.setUsers(new ArrayList<>());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setPassword("encodedpassword");
        userEntity.setEmail("testemail@example.com");
        userEntity.setAuthority(authorityEntity);

        when(this.userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        when(this.authorityRepository.findByAuthority(any(Authority.class))).thenReturn(authorityEntity);

        when(this.modelMapper.map(any(UserRegisterFormDto.class), eq(UserEntity.class))).thenReturn(userEntity);

        when(this.passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedpassword");


        this.userServiceToTest.addUser(userRegisterFormDto);

        verify(this.userRepository).save(userEntity);
        verify(this.authorityRepository).save(authorityEntity);

    }

    @Test
    void test_AddUser_User_Password_Is_Encrypted()
    {
        UserRegisterFormDto userRegisterFormDto = new UserRegisterFormDto() ;
        userRegisterFormDto.setUsername("testuser");
        userRegisterFormDto.setPassword("testpassword");
        userRegisterFormDto.setEmail("testemail@example.com");
        userRegisterFormDto.setAuthorityName("USER");

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(Authority.USER);
        authorityEntity.setUsers(new ArrayList<>());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setPassword("encodedpassword");
        userEntity.setEmail("testemail@example.com");
        userEntity.setAuthority(authorityEntity);

        when(this.userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        when(this.authorityRepository.findByAuthority(any(Authority.class))).thenReturn(authorityEntity);

        when(this.modelMapper.map(any(UserRegisterFormDto.class), eq(UserEntity.class))).thenReturn(userEntity);

        when(this.passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedpassword");

        this.userServiceToTest.addUser(userRegisterFormDto);

        verify(this.passwordEncoder).encode(any(CharSequence.class)) ;
        assertTrue(userEntity.getPassword().equals("encodedpassword")) ;
    }

    @Test
    void test_AddUser_Authority_Has_User_Added()
    {
        UserRegisterFormDto userRegisterFormDto = new UserRegisterFormDto() ;
        userRegisterFormDto.setUsername("testuser");
        userRegisterFormDto.setPassword("testpassword");
        userRegisterFormDto.setEmail("testemail@example.com");
        userRegisterFormDto.setAuthorityName("USER");

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(Authority.USER);
        authorityEntity.setUsers(new ArrayList<>());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setPassword("encodedpassword");
        userEntity.setEmail("testemail@example.com");
        userEntity.setAuthority(authorityEntity);

        when(this.userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        when(this.authorityRepository.findByAuthority(any(Authority.class))).thenReturn(authorityEntity);

        when(this.modelMapper.map(any(UserRegisterFormDto.class), eq(UserEntity.class))).thenReturn(userEntity);

        when(this.passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedpassword");

        this.userServiceToTest.addUser(userRegisterFormDto);

        assertTrue(authorityEntity.getUsers().size() == 1);
        assertTrue(authorityEntity.getUsers().contains(userEntity));
    }

    @Test
    void test_AddUser_User_Has_Authority_Added()
    {
        UserRegisterFormDto userRegisterFormDto = new UserRegisterFormDto() ;
        userRegisterFormDto.setUsername("testuser");
        userRegisterFormDto.setPassword("testpassword");
        userRegisterFormDto.setEmail("testemail@example.com");
        userRegisterFormDto.setAuthorityName("USER");

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(Authority.USER);
        authorityEntity.setUsers(new ArrayList<>());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setPassword("encodedpassword");
        userEntity.setEmail("testemail@example.com");


        when(this.userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        when(this.authorityRepository.findByAuthority(any(Authority.class))).thenReturn(authorityEntity);

        when(this.modelMapper.map(any(UserRegisterFormDto.class), eq(UserEntity.class))).thenReturn(userEntity);

        when(this.passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedpassword");

        this.userServiceToTest.addUser(userRegisterFormDto);

        assertTrue(userEntity.getAuthority() != null);
        assertTrue(userEntity.getAuthority().equals(authorityEntity));
    }

    @Test
    void test_GetLoggedUser()
    {
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(Authority.USER);
        authorityEntity.setUsers(new ArrayList<>());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testuser");
        userEntity.setPassword("encodedpassword");
        userEntity.setEmail("testemail@example.com");
        userEntity.setAuthority(authorityEntity);

        SecurityContext securityContext = mock(SecurityContext.class) ;

        SecurityContextHolder.setContext(securityContext);

        Authentication authentication = mock(Authentication.class) ;

        when(securityContext.getAuthentication()).thenReturn(authentication) ;

        when(authentication.getName()).thenReturn("testuser") ;

        when(this.userRepository.findByUsername("testuser")).thenReturn(userEntity) ;

        assertTrue(userEntity.equals(this.userServiceToTest.getLoggeedUser()));
    }
}
