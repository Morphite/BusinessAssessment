package com.diplome.businessassessment.controller;

import com.diplome.businessassessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String homePage() {
        return "index";
    }

    @RequestMapping("/assessment")
    public String create() {
        return "createOrUpdateObjectForm";
    }
}
