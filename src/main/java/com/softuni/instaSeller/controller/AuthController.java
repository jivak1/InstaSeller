package com.softuni.instaSeller.controller;

import com.softuni.instaSeller.model.dto.UserLoginFormDto;
import com.softuni.instaSeller.model.dto.UserRegisterFormDto;
import com.softuni.instaSeller.model.entity.AuthorityEntity;
import com.softuni.instaSeller.model.entity.UserEntity;
import com.softuni.instaSeller.model.enums.Authority;
import com.softuni.instaSeller.repository.AuthorityRepository;
import com.softuni.instaSeller.repository.UserRepository;
import com.softuni.instaSeller.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController{
    @Autowired
    private UserService userService ;

    @Autowired
    private AuthorityRepository authorityRepository ;




    @GetMapping("/login")
    public String getLogin(ModelAndView modelAndView)
    {

        return "login" ;
    }

    @PostMapping("/login-error")
    public String postLoginError(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                 RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/auth/login" ;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView)
    {
        List<AuthorityEntity> authorities = this.authorityRepository.findAll();

        modelAndView.addObject("user", new UserRegisterFormDto()) ;
        modelAndView.addObject("authorities", authorities) ;

        return super.view("register", modelAndView) ;
    }

    @PostMapping("/register")
    public ModelAndView postRegister(ModelAndView modelAndView,
                                     @Valid @ModelAttribute("user") UserRegisterFormDto userRegisterFormDto,
                                     BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            List<AuthorityEntity> authorities = this.authorityRepository.findAll();
            modelAndView.addObject("user", userRegisterFormDto) ;
            modelAndView.addObject("authorities", authorities) ;
            return super.view("register", modelAndView) ;
        }else
        {
            this.userService.addUser(userRegisterFormDto);

            return super.redirect("/login") ;
        }
    }

}
