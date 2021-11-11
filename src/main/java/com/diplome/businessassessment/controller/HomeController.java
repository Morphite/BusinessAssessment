package com.diplome.businessassessment.controller;

import com.diplome.businessassessment.helper.AssessmentHelper;
import com.diplome.businessassessment.repository.MetricRepository;
import com.diplome.businessassessment.repository.FunctionalityRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import com.diplome.businessassessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FunctionalityRepository functionalityRepository;

    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private AssessmentHelper helper;

    @RequestMapping("/")
    public String homePage() {
        return "index";
    }
}
