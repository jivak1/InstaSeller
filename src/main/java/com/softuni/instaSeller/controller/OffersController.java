package com.softuni.instaSeller.controller;

import com.softuni.instaSeller.model.dto.OfferAddFormDto;
import com.softuni.instaSeller.model.entity.OfferEntity;
import com.softuni.instaSeller.model.entity.PageEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.repository.OfferRepository;
import com.softuni.instaSeller.repository.PageRepository;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.OfferService;
import com.softuni.instaSeller.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OffersController extends BaseController{
    @Autowired
    private UserService userService ;
    @Autowired
    private OfferService offerService ;
    @Autowired
    private PageRepository pageRepository ;
    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private OfferRepository offerRepository ;

    @Autowired
    private ModelMapper modelMapper ;

    @GetMapping("/add")
    public ModelAndView getOffersAdd(ModelAndView modelAndView)
    {
        UserEntity user = this.userService.getLoggeedUser();
        List<PageEntity> pages = user.getPages() ;

        modelAndView.addObject("offer", new OfferAddFormDto()) ;
        modelAndView.addObject("pages", pages) ;

        return super.view("offers-add", modelAndView) ;
    }

    @PostMapping("/add")
    public ModelAndView postOffersAdd(ModelAndView modelAndView,
                                      @Valid @ModelAttribute("offer") OfferAddFormDto offerAddFormDto,
                                      BindingResult bindingResult)
    {
        UserEntity user = this.userService.getLoggeedUser();

        if(bindingResult.hasErrors())
        {
            List<PageEntity> pages = user.getPages() ;

            modelAndView.addObject("offer", offerAddFormDto) ;
            modelAndView.addObject("pages", pages) ;

            return super.view("offers-add", modelAndView) ;
        }else
        {
            OfferEntity offer = this.modelMapper.map(offerAddFormDto, OfferEntity.class) ;
            PageEntity page = this.pageRepository.findByName(offerAddFormDto.getPageName()) ;

            this.offerService.addOffer(offer, page, user);

            return super.redirect("/profile") ;
        }
    }

    @GetMapping("/shop")
    public ModelAndView getOffersShop(ModelAndView modelAndView)
    {
        UserEntity user = this.userService.getLoggeedUser();
        List<OfferEntity> offers = this.offerRepository.findAllBySellerNot(user) ;

        modelAndView.addObject("offers", offers) ;

        return super.view("shop", modelAndView) ;
    }

    @PostMapping("/shop/buy")
    public ModelAndView postOffersBuy(@RequestParam("id") Long id)
    {
        OfferEntity offer = this.offerRepository.findById(id).get() ;
        PageEntity page = offer.getPage() ;
        UserEntity seller = offer.getSeller() ;
        UserEntity buyer = this.userService.getLoggeedUser();

       this.offerService.completeOffer(offer, page, seller, buyer);

        return super.redirect("/profile") ;
    }

    @DeleteMapping("/delete")
    public ModelAndView deleteOffer(@RequestParam("id") Long id)
    {
        OfferEntity offer = this.offerRepository.findById(id).get() ;
        PageEntity page = offer.getPage() ;
        UserEntity user = offer.getSeller() ;

        this.offerService.deleteOffer(offer, page, user);


        return super.redirect("/profile") ;
    }



}
