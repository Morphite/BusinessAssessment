package com.diplome.businessassessment.controller;

import com.diplome.businessassessment.model.AbstractMetric;
import com.diplome.businessassessment.model.Question;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.MetricRepository;
import com.diplome.businessassessment.repository.QuestionRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import com.diplome.businessassessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private MetricRepository metricRepository;

    @RequestMapping("/")
    public String homePage() {
        return "index";
    }

    @RequestMapping("/assessment")
    public String getAssessment(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "assessment";
    }

    @RequestMapping("/question")
    public String createQuestion() {
        Map<String, AbstractMetric> metricMap = metricRepository.findAllMetricsMapWithNameAsKey();



        return "assessment";
    }
}
