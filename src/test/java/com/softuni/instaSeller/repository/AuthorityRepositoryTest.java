package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.AuthorityEntity;
import com.softuni.instaSeller.model.enums.Authority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorityRepositoryTest {
    @Autowired
    private AuthorityRepository authorityRepository ;


    @Test
    void test_findByAuthority()
    {
        AuthorityEntity authority = new AuthorityEntity() ;
        authority.setAuthority(Authority.TEST);

        this.authorityRepository.save(authority) ;

        AuthorityEntity authorityReceived = this.authorityRepository.findByAuthority(authority.getAuthority()) ;

        this.authorityRepository.delete(authority) ;

        assertTrue(authorityReceived.equals(authority)) ;
    }
}
