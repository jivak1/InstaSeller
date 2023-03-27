package com.softuni.instaSeller.service.impl;

import com.softuni.instaSeller.model.dto.PageAddFormDto;
import com.softuni.instaSeller.model.entity.NicheEntity;
import com.softuni.instaSeller.model.entity.OfferEntity;
import com.softuni.instaSeller.model.entity.PageEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.model.enums.Niche;
import com.softuni.instaSeller.repository.NicheRepository;
import com.softuni.instaSeller.repository.OfferRepository;
import com.softuni.instaSeller.repository.PageRepository;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private OfferRepository offerRepository ;

    @Autowired
    private NicheRepository nicheRepository ;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private PageRepository pageRepository ;

    @Override
    public void deletePage(PageEntity page, List<OfferEntity> offersWithPage, UserEntity user) {
        this.offerRepository.deleteAll(offersWithPage);

        user.getPages().remove(page);

        this.userRepository.save(user) ;

        this.pageRepository.delete(page);
    }

    @Override
    public void savePage(PageEntity page, NicheEntity niche, UserEntity user) {

        page.setNiche(niche);

        page.setOwner(user);

        niche.getPages().add(page) ;

        user.getPages().add(page) ;

        this.pageRepository.save(page) ;
        this.nicheRepository.save(niche) ;
        this.userRepository.save(user) ;
    }
}
