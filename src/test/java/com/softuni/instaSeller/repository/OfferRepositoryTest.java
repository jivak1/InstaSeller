package com.softuni.instaSeller.repository;

import com.softuni.instaSeller.model.entity.OfferEntity;
import com.softuni.instaSeller.model.entity.PageEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OfferRepositoryTest {
    @Autowired
    private OfferRepository offerRepository ;

    @Autowired
    private PageRepository pageRepository ;

    @Autowired
    private UserRepository userRepository ;
    @Test
    void test_FindAllByPage()
    {
        PageEntity page = new PageEntity() ;
        page.setName("testname") ;
        page.setFollowers(10000);
        page.setFollowing(2000);
        page.setImageURL("www.testimage.com");
        page.setOffers(new ArrayList<>());

        PageEntity page2 = new PageEntity() ;
        page2.setName("testname2") ;
        page2.setFollowers(2000);
        page2.setFollowing(3000);
        page2.setImageURL("www.testimage2.com");
        page2.setOffers(new ArrayList<>());

        OfferEntity firstOffer = new OfferEntity() ;
        firstOffer.setPage(page);
        firstOffer.setPrice(123.45);

        OfferEntity secondOffer = new OfferEntity() ;
        secondOffer.setPage(page);
        secondOffer.setPrice(456.78);

        OfferEntity thirdOffer = new OfferEntity() ;
        thirdOffer.setPage(page2);
        thirdOffer.setPrice(123.45);

        page.getOffers().add(firstOffer) ;
        page.getOffers().add(secondOffer) ;
        page2.getOffers().add(thirdOffer) ;

        this.offerRepository.save(firstOffer) ;
        this.offerRepository.save(secondOffer) ;
        this.offerRepository.save(thirdOffer) ;

        this.pageRepository.save(page) ;
        this.pageRepository.save(page2) ;

        List<OfferEntity> offersWithPage = this.offerRepository.findAllByPage(page) ;
        List<OfferEntity> offersWithPage2 = this.offerRepository.findAllByPage(page2) ;

        assertTrue(offersWithPage != null);
        assertTrue(offersWithPage.size() == 2);
        assertTrue(offersWithPage.contains(firstOffer));
        assertTrue(offersWithPage.contains(secondOffer));

        assertTrue(offersWithPage2 != null);
        assertTrue(offersWithPage2.size() == 1);
        assertTrue(offersWithPage2.contains(thirdOffer));

        this.offerRepository.delete(firstOffer) ;
        this.offerRepository.delete(secondOffer) ;
        this.offerRepository.delete(thirdOffer) ;

        this.pageRepository.delete(page) ;
        this.pageRepository.delete(page2) ;
    }

    @Test
    void test_FindAllBySellerNot()
    {
        UserEntity seller1 = new UserEntity() ;
        seller1.setUsername("testusername");
        seller1.setPassword("testpassword");
        seller1.setEmail("testemail");

        UserEntity seller2 = new UserEntity() ;
        seller2.setUsername("testusername2");
        seller2.setPassword("testpassword2");
        seller2.setEmail("testemail2");

        OfferEntity firstOffer = new OfferEntity() ;
        firstOffer.setPrice(123.45);
        firstOffer.setSeller(seller1);

        OfferEntity secondOffer = new OfferEntity() ;
        secondOffer.setPrice(456.78);
        secondOffer.setSeller(seller1);

        OfferEntity thirdOffer = new OfferEntity() ;
        thirdOffer.setPrice(123.45);
        thirdOffer.setSeller(seller2);


        this.offerRepository.save(firstOffer) ;
        this.offerRepository.save(secondOffer) ;
        this.offerRepository.save(thirdOffer) ;


        this.userRepository.save(seller1) ;
        this.userRepository.save(seller2) ;

        List<OfferEntity> offersWithSellerNotSeller2 = this.offerRepository.findAllBySellerNot(seller2) ;
        List<OfferEntity> offersWithSellerNotSeller1 = this.offerRepository.findAllBySellerNot(seller1) ;

        assertTrue(offersWithSellerNotSeller2 != null);
        assertTrue(offersWithSellerNotSeller2.contains(firstOffer));
        assertTrue(offersWithSellerNotSeller2.contains(secondOffer));

        assertTrue(offersWithSellerNotSeller1 != null);
        assertTrue(offersWithSellerNotSeller1.contains(thirdOffer));


        this.offerRepository.delete(firstOffer) ;
        this.offerRepository.delete(secondOffer) ;
        this.offerRepository.delete(thirdOffer) ;

        this.userRepository.delete(seller1) ;
        this.userRepository.delete(seller2) ;
    }
}
