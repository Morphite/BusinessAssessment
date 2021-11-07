package com.diplome.businessassessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin-panel")
    public String getAdminPage(){
        return "admin-panel";
    }
}
