package com.softuni.instaSeller.service;

import com.softuni.instaSeller.model.dto.PageAddFormDto;
import com.softuni.instaSeller.model.entity.NicheEntity;
import com.softuni.instaSeller.model.entity.OfferEntity;
import com.softuni.instaSeller.model.entity.PageEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.repository.OfferRepository;
import com.softuni.instaSeller.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PageService {

    public void deletePage(PageEntity page, List<OfferEntity> offersWithPage, UserEntity user) ;

    public void savePage(PageEntity page, NicheEntity niche, UserEntity user) ;
}
