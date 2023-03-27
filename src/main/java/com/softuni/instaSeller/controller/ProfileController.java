package com.softuni.instaSeller.controller;

import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseController{
    @Autowired
    private UserService userService ;
    @Autowired
    private UserRepository userRepository ;

    @GetMapping()
    public ModelAndView getProfile(ModelAndView modelAndView)
    {
        UserEntity user = this.userService.getLoggeedUser();
        modelAndView.addObject("pages", user.getPages()) ;
        modelAndView.addObject("offers", user.getOffers()) ;
        return super.view("profile", modelAndView) ;
    }
}
