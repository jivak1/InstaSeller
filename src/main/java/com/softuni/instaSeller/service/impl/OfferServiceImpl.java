package com.softuni.instaSeller.service.impl;

import com.softuni.instaSeller.model.entity.OfferEntity;
import com.softuni.instaSeller.model.entity.PageEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.repository.OfferRepository;
import com.softuni.instaSeller.repository.PageRepository;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository ;
    @Autowired
    private PageRepository pageRepository ;

    @Autowired
    private UserRepository userRepository ;
    @Override
    public void addOffer(OfferEntity offer, PageEntity page, UserEntity user) {
        offer.setPage(page);
        offer.setSeller(user);

        user.getOffers().add(offer) ;

        page.getOffers().add(offer) ;

        this.offerRepository.save(offer) ;
        this.pageRepository.save(page) ;
        this.userRepository.save(user) ;
    }

    @Override
    public void completeOffer(OfferEntity offer, PageEntity page, UserEntity seller, UserEntity buyer) {
        List<OfferEntity> offersToDelete = new ArrayList<>() ;
        offersToDelete = this.offerRepository.findAllByPage(page) ;

        seller.getOffers().removeAll(offersToDelete) ;
        seller.getPages().remove(page) ;
        buyer.getPages().add(page) ;
        page.setOwner(buyer);

        this.offerRepository.deleteAll(offersToDelete);
        this.userRepository.save(seller) ;
        this.userRepository.save(buyer) ;
        this.pageRepository.save(page) ;
    }

    @Override
    public void deleteOffer(OfferEntity offer, PageEntity page, UserEntity user) {
        page.getOffers().remove(offer) ;
        user.getOffers().remove(offer) ;
        this.offerRepository.delete(offer);

        this.userRepository.save(user) ;
        this.pageRepository.save(page) ;
    }
}
