package com.softuni.instaSeller.service;

import com.softuni.instaSeller.model.entity.OfferEntity;
import com.softuni.instaSeller.model.entity.PageEntity;
import com.softuni.instaSeller.model.entity.UserEntity;

public interface OfferService {
    public void addOffer(OfferEntity offer, PageEntity page, UserEntity user) ;

    public void completeOffer(OfferEntity offer, PageEntity page, UserEntity seller,  UserEntity buyer) ;

    public void deleteOffer(OfferEntity offer, PageEntity page, UserEntity user) ;
}
