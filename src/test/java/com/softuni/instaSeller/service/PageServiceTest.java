package com.softuni.instaSeller.service;

import com.softuni.instaSeller.model.entity.*;
import com.softuni.instaSeller.model.enums.Authority;
import com.softuni.instaSeller.model.enums.Niche;
import com.softuni.instaSeller.repository.NicheRepository;
import com.softuni.instaSeller.repository.OfferRepository;
import com.softuni.instaSeller.repository.PageRepository;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.impl.PageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class PageServiceTest {
    @Mock
    private OfferRepository offerRepository ;

    @Mock
    private NicheRepository nicheRepository ;

    @Mock
    private UserRepository userRepository ;

    @Mock
    private PageRepository pageRepository ;

    @InjectMocks
    private PageServiceImpl pageServiceToTest ;

    @Test
    void test_SavePage_Saves_Page_And_Niche_And_User()
    {
        PageEntity page = new PageEntity() ;
        page.setName("testname") ;
        page.setFollowers(10000);
        page.setFollowing(2000);
        page.setImageURL("www.testimage.com");
        page.setOffers(new ArrayList<>());

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(Authority.USER);
        authorityEntity.setUsers(new ArrayList<>());

        UserEntity owner = new UserEntity();
        owner.setUsername("testuser");
        owner.setPassword("encodedpassword");
        owner.setEmail("testemail@example.com");
        owner.setAuthority(authorityEntity);
        owner.setPages(new ArrayList<>());


        NicheEntity niche = new NicheEntity() ;
        niche.setNiche(Niche.ANIMAL);
        niche.setPages(new ArrayList<>());


        this.pageServiceToTest.savePage(page, niche, owner);

        verify(this.pageRepository).save(page) ;
        verify(this.nicheRepository).save(niche) ;
        verify(this.userRepository).save(owner) ;
    }

    @Test
    void test_SavePage()
    {
        PageEntity page = new PageEntity() ;
        page.setName("testname") ;
        page.setFollowers(10000);
        page.setFollowing(2000);
        page.setImageURL("www.testimage.com");
        page.setOffers(new ArrayList<>());

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(Authority.USER);
        authorityEntity.setUsers(new ArrayList<>());

        UserEntity owner = new UserEntity();
        owner.setUsername("testuser");
        owner.setPassword("encodedpassword");
        owner.setEmail("testemail@example.com");
        owner.setAuthority(authorityEntity);
        owner.setPages(new ArrayList<>());


        NicheEntity niche = new NicheEntity() ;
        niche.setNiche(Niche.ANIMAL);
        niche.setPages(new ArrayList<>());

        this.pageServiceToTest.savePage(page, niche, owner);

        assertFalse(owner.getPages().isEmpty());
        assertFalse(niche.getPages().isEmpty());

        assertTrue(page.getNiche().equals(niche));
        assertTrue(page.getOwner().equals(owner));
        assertTrue(owner.getPages().get(0).equals(page));
        assertTrue(niche.getPages().get(0).equals(page));
    }

    @Test
    void test_DeletePage_Deletes_All_Offers_And_Pages()
    {
        PageEntity page = new PageEntity() ;
        page.setName("testname") ;
        page.setFollowers(10000);
        page.setFollowing(2000);
        page.setImageURL("www.testimage.com");
        page.setOffers(new ArrayList<>());

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(Authority.USER);
        authorityEntity.setUsers(new ArrayList<>());

        UserEntity seller = new UserEntity();
        seller.setUsername("testuser");
        seller.setPassword("encodedpassword");
        seller.setEmail("testemail@example.com");
        seller.setAuthority(authorityEntity);
        seller.setPages(new ArrayList<>());

        List<OfferEntity> offersToDelete = new ArrayList<>() ;

        OfferEntity offer = new OfferEntity() ;
        offer.setSeller(seller);
        offer.setPage(page);
        offer.setPrice(1000);

        this.pageServiceToTest.deletePage(page, offersToDelete, seller);

        verify(this.offerRepository).deleteAll(offersToDelete) ;
        verify(this.pageRepository).delete(page) ;
        verify(this.userRepository).save(seller) ;
    }

    @Test
    void test_DeletePage_Removes_Page_From_User()
    {
        PageEntity page = new PageEntity() ;
        page.setName("testname") ;
        page.setFollowers(10000);
        page.setFollowing(2000);
        page.setImageURL("www.testimage.com");
        page.setOffers(new ArrayList<>());

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority(Authority.USER);
        authorityEntity.setUsers(new ArrayList<>());

        UserEntity seller = new UserEntity();
        seller.setUsername("testuser");
        seller.setPassword("encodedpassword");
        seller.setEmail("testemail@example.com");
        seller.setAuthority(authorityEntity);
        seller.setPages(new ArrayList<>());
        seller.getPages().add(page) ;

        List<OfferEntity> offersToDelete = new ArrayList<>() ;

        OfferEntity offer = new OfferEntity() ;
        offer.setSeller(seller);
        offer.setPage(page);
        offer.setPrice(1000);

        this.pageServiceToTest.deletePage(page, offersToDelete, seller);

        assertFalse(seller.getPages().contains(page));
    }
}
