package com.softuni.instaSeller.controller;

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

import java.util.List;

@Controller
@RequestMapping("/pages")
public class PagesController extends BaseController{
    @Autowired
    private UserService userService ;

    @Autowired
    private PageService pageService ;
    @Autowired
    private NicheRepository nicheRepository ;

    @Autowired
    private OfferRepository offerRepository ;

    @Autowired
    private PageRepository pageRepository ;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private ModelMapper modelMapper ;

    @GetMapping("/add")
    public ModelAndView getAddPage(ModelAndView modelAndView)
    {
        modelAndView.addObject("page", new PageAddFormDto()) ;
        modelAndView.addObject("niches", this.nicheRepository.findAll()) ;

        return super.view("pages-add", modelAndView) ;
    }

    @PostMapping("/add")
    public ModelAndView postAddPage(ModelAndView modelAndView,
                                    @Valid @ModelAttribute("page") PageAddFormDto pageAddFormDto,
                                    BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            modelAndView.addObject("page", pageAddFormDto) ;
            modelAndView.addObject("niches", this.nicheRepository.findAll()) ;

            return super.view("pages-add", modelAndView) ;
        }else
        {
            PageEntity page = this.modelMapper.map(pageAddFormDto, PageEntity.class);

            NicheEntity niche = this.nicheRepository.findByNiche(Niche.valueOf(pageAddFormDto.getNicheName())) ;

            UserEntity user = this.userService.getLoggeedUser() ;

           this.pageService.savePage(page, niche, user);

            return super.redirect("/profile") ;
        }
    }

    @DeleteMapping(value = "/delete")
    public ModelAndView deletePage(@RequestParam("id") Long id)
    {
        PageEntity page = this.pageRepository.findById(id).get() ;
        List<OfferEntity> offersWithPage = this.offerRepository.findAllByPage(page) ;
        UserEntity user = this.userService.getLoggeedUser() ;


        this.pageService.deletePage(page, offersWithPage, user);

        this.userRepository.save(user) ;

        return super.redirect("/profile") ;
    }

}
