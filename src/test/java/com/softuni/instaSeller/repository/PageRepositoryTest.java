package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.NicheEntity;
import com.softuni.instaSeller.model.entity.PageEntity;
import com.softuni.instaSeller.model.enums.Niche;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PageRepositoryTest {
    @Autowired
    private PageRepository pageRepository ;

    @Test
    void test_FindByName()
    {
        PageEntity page = new PageEntity() ;
        page.setName("testname");
        page.setFollowing(123);
        page.setFollowers(456);
        page.setImageURL("image.com");

        this.pageRepository.save(page) ;

        PageEntity receievedPage = this.pageRepository.findByName(page.getName()) ;

        this.pageRepository.delete(page) ;
    }

}
