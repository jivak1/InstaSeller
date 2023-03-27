package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.NicheEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.model.enums.Niche;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NicheRepositoryTest {
    @Autowired
    private NicheRepository nicheRepository ;

    @Test
    void test_FindByNiche()
    {
        NicheEntity niche = new NicheEntity() ;
        niche.setNiche(Niche.TEST);

        this.nicheRepository.save(niche) ;

        NicheEntity receivedNiche = this.nicheRepository.findByNiche(niche.getNiche()) ;

        this.nicheRepository.delete(niche) ;

        assertTrue(niche.equals(receivedNiche));
    }

}
