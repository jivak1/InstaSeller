package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.AuthorityEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.model.enums.Authority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository ;


    @Test
    void test_findByUsername()
    {
        String USERNAME = "test" ;
        UserEntity user = new UserEntity() ;
        user.setUsername(USERNAME);
        user.setEmail("email@email.com");
        user.setPassword("testpassword");

        this.userRepository.save(user) ;

        UserEntity recivedUser = this.userRepository.findByUsername(USERNAME) ;

        this.userRepository.delete(user) ;

        assertTrue(user.equals(recivedUser));
    }

    @Test
    void test_findByEmail()
    {
        String EMAIL = "email@email.com" ;
        UserEntity user = new UserEntity() ;
        user.setUsername("testusername");
        user.setEmail(EMAIL);
        user.setPassword("testpassword");

        this.userRepository.save(user) ;

        UserEntity recivedUser = this.userRepository.findByEmail(EMAIL) ;

        this.userRepository.delete(user) ;

        assertTrue(user.equals(recivedUser));
    }
}
