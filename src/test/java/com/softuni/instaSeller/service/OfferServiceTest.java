package com.softuni.instaSeller.service;


import com.softuni.instaSeller.model.entity.*;
import com.softuni.instaSeller.model.enums.Authority;
import com.softuni.instaSeller.model.enums.Niche;
import com.softuni.instaSeller.repository.OfferRepository;
import com.softuni.instaSeller.repository.PageRepository;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.impl.OfferServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {
    @Mock
    private OfferRepository offerRepository ;
    @Mock
    private PageRepository pageRepository ;

    @Mock
    private UserRepository userRepository ;

    @InjectMocks
    private OfferServiceImpl offerServiceToTest ;

    @Test
    void test_AddOffer_Saves_User_And_Page_And_Offer()
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

        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setEmail("testemail@example.com");
        user.setAuthority(authorityEntity);
        user.setPages(new ArrayList<>());
        user.setOffers(new ArrayList<>());
        user.getPages().add(page) ;

        authorityEntity.getUsers().add(user) ;


        NicheEntity niche = new NicheEntity() ;
        niche.setNiche(Niche.ANIMAL);
        niche.setPages(new ArrayList<>());

        OfferEntity offer = new OfferEntity() ;
        offer.setPrice(123.45);

        this.offerServiceToTest.addOffer(offer, page, user);

        verify(this.offerRepository).save(offer) ;
        verify(this.pageRepository).save(page) ;
        verify(this.userRepository).save(user) ;
    }

    @Test
    void test_AddOffer()
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

        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setEmail("testemail@example.com");
        user.setAuthority(authorityEntity);
        user.setPages(new ArrayList<>());
        user.setOffers(new ArrayList<>());
        user.getPages().add(page) ;

        authorityEntity.getUsers().add(user) ;
        page.setOwner(user);

        NicheEntity niche = new NicheEntity() ;
        niche.setNiche(Niche.ANIMAL);
        niche.setPages(new ArrayList<>());

        OfferEntity offer = new OfferEntity() ;
        offer.setPrice(123.45);

        this.offerServiceToTest.addOffer(offer, page, user);

        assertTrue(offer.getPage().equals(page)) ;
        assertTrue(offer.getSeller().equals(user));

        assertFalse(page.getOffers().isEmpty());
        assertTrue(page.getOffers().get(0).equals(offer));

        assertFalse(user.getOffers().isEmpty());
        assertTrue(user.getOffers().get(0).equals(offer));
    }

    @Test
    void test_CompleteOffer_Saves_Buyer_And_Seller_And_Page()
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

        UserEntity buyer = new UserEntity();
        buyer.setUsername("testuser");
        buyer.setPassword("encodedpassword");
        buyer.setEmail("testemail@example.com");
        buyer.setAuthority(authorityEntity);
        buyer.setPages(new ArrayList<>());
        buyer.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(buyer) ;

        UserEntity seller = new UserEntity();
        seller.setUsername("testuser");
        seller.setPassword("encodedpassword");
        seller.setEmail("testemail@example.com");
        seller.setAuthority(authorityEntity);
        seller.setPages(new ArrayList<>());
        seller.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(seller) ;


        OfferEntity offer = new OfferEntity() ;
        offer.setPrice(123.45);

        ArrayList<OfferEntity> offersToDelete = new ArrayList<>() ;
        offersToDelete.add(offer) ;

        offer.setSeller(seller);
        offer.setPage(page);
        offer.setPrice(123.45);
        seller.getPages().add(page) ;
        seller.getOffers().add(offer) ;
        page.getOffers().add(offer) ;
        page.setOwner(seller);


        when(this.offerRepository.findAllByPage(page)).thenReturn(offersToDelete) ;

        this.offerServiceToTest.completeOffer(offer, page, seller, buyer);

        verify(this.offerRepository).deleteAll(any(ArrayList.class));
        verify(this.userRepository).save(seller) ;
        verify(this.userRepository).save(buyer) ;
        verify(this.pageRepository).save(page) ;

    }

    @Test
    void test_CompleteOffer_Removes_Page_And_Offer_From_Seller()
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

        UserEntity buyer = new UserEntity();
        buyer.setUsername("testuser");
        buyer.setPassword("encodedpassword");
        buyer.setEmail("testemail@example.com");
        buyer.setAuthority(authorityEntity);
        buyer.setPages(new ArrayList<>());
        buyer.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(buyer) ;

        UserEntity seller = new UserEntity();
        seller.setUsername("testuser");
        seller.setPassword("encodedpassword");
        seller.setEmail("testemail@example.com");
        seller.setAuthority(authorityEntity);
        seller.setPages(new ArrayList<>());
        seller.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(seller) ;


        OfferEntity offer = new OfferEntity() ;
        offer.setPrice(123.45);

        ArrayList<OfferEntity> offersToDelete = new ArrayList<>() ;
        offersToDelete.add(offer) ;

        offer.setSeller(seller);
        offer.setPage(page);
        offer.setPrice(123.45);
        seller.getPages().add(page) ;
        seller.getOffers().add(offer) ;
        page.getOffers().add(offer) ;
        page.setOwner(seller);


        when(this.offerRepository.findAllByPage(page)).thenReturn(offersToDelete) ;

        this.offerServiceToTest.completeOffer(offer, page, seller, buyer);

        assertFalse(seller.getOffers().contains(offer));
        assertFalse(seller.getPages().contains(page)) ;
    }

    @Test
    void test_CompleteOffer_Gives_Page_To_Buyer()
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

        UserEntity buyer = new UserEntity();
        buyer.setUsername("testuser");
        buyer.setPassword("encodedpassword");
        buyer.setEmail("testemail@example.com");
        buyer.setAuthority(authorityEntity);
        buyer.setPages(new ArrayList<>());
        buyer.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(buyer) ;

        UserEntity seller = new UserEntity();
        seller.setUsername("testuser");
        seller.setPassword("encodedpassword");
        seller.setEmail("testemail@example.com");
        seller.setAuthority(authorityEntity);
        seller.setPages(new ArrayList<>());
        seller.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(seller) ;


        OfferEntity offer = new OfferEntity() ;
        offer.setPrice(123.45);

        ArrayList<OfferEntity> offersToDelete = new ArrayList<>() ;
        offersToDelete.add(offer) ;

        offer.setSeller(seller);
        offer.setPage(page);
        offer.setPrice(123.45);
        seller.getPages().add(page) ;
        seller.getOffers().add(offer) ;
        page.getOffers().add(offer) ;
        page.setOwner(seller);


        when(this.offerRepository.findAllByPage(page)).thenReturn(offersToDelete) ;

        this.offerServiceToTest.completeOffer(offer, page, seller, buyer);

        assertTrue(buyer.getPages().contains(page));
        assertTrue(page.getOwner().equals(buyer));
    }

    @Test
    void test_DeleteOffer_DeletesPage()
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

        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setEmail("testemail@example.com");
        user.setAuthority(authorityEntity);
        user.setPages(new ArrayList<>());
        user.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(user) ;


        OfferEntity offer = new OfferEntity() ;
        offer.setPrice(123.45);

        ArrayList<OfferEntity> offersToDelete = new ArrayList<>() ;
        offersToDelete.add(offer) ;

        offer.setSeller(user);
        offer.setPage(page);
        offer.setPrice(123.45);
        user.getPages().add(page) ;
        user.getOffers().add(offer) ;
        page.getOffers().add(offer) ;
        page.setOwner(user);

        this.offerServiceToTest.deleteOffer(offer, page, user);

        verify(this.offerRepository).delete(offer) ;
    }

    @Test
    void test_DeleteOffer_Persists_User_And_Page()
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

        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setEmail("testemail@example.com");
        user.setAuthority(authorityEntity);
        user.setPages(new ArrayList<>());
        user.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(user) ;


        OfferEntity offer = new OfferEntity() ;
        offer.setPrice(123.45);

        ArrayList<OfferEntity> offersToDelete = new ArrayList<>() ;
        offersToDelete.add(offer) ;

        offer.setSeller(user);
        offer.setPage(page);
        offer.setPrice(123.45);
        user.getPages().add(page) ;
        user.getOffers().add(offer) ;
        page.getOffers().add(offer) ;
        page.setOwner(user);

        this.offerServiceToTest.deleteOffer(offer, page, user);

        verify(this.pageRepository).save(page) ;
        verify(this.userRepository).save(user) ;
    }

    @Test
    void test_DeleteOffer_Removes_Offer_From_Page()
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

        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setEmail("testemail@example.com");
        user.setAuthority(authorityEntity);
        user.setPages(new ArrayList<>());
        user.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(user) ;


        OfferEntity offer = new OfferEntity() ;
        offer.setPrice(123.45);

        ArrayList<OfferEntity> offersToDelete = new ArrayList<>() ;
        offersToDelete.add(offer) ;

        offer.setSeller(user);
        offer.setPage(page);
        offer.setPrice(123.45);
        user.getPages().add(page) ;
        user.getOffers().add(offer) ;
        page.getOffers().add(offer) ;
        page.setOwner(user);

        this.offerServiceToTest.deleteOffer(offer, page, user);

        assertFalse(page.getOffers().contains(offer));
    }

    @Test
    void test_DeleteOffer_Removes_Offer_From_User()
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

        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("encodedpassword");
        user.setEmail("testemail@example.com");
        user.setAuthority(authorityEntity);
        user.setPages(new ArrayList<>());
        user.setOffers(new ArrayList<>());

        authorityEntity.getUsers().add(user) ;


        OfferEntity offer = new OfferEntity() ;
        offer.setPrice(123.45);

        ArrayList<OfferEntity> offersToDelete = new ArrayList<>() ;
        offersToDelete.add(offer) ;

        offer.setSeller(user);
        offer.setPage(page);
        offer.setPrice(123.45);
        user.getPages().add(page) ;
        user.getOffers().add(offer) ;
        page.getOffers().add(offer) ;
        page.setOwner(user);

        this.offerServiceToTest.deleteOffer(offer, page, user);

        assertFalse(user.getOffers().contains(offer));
    }
}
