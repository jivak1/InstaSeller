package com.softuni.instaSeller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController{

    @GetMapping("/")
    public ModelAndView getIndex()
    {
        return super.view("index") ;
    }

    @GetMapping("/home")
    public ModelAndView getHome()
    {

        return super.view("home") ;
    }
}
