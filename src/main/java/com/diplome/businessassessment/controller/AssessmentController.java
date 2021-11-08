package com.diplome.businessassessment.controller;

import com.diplome.businessassessment.helper.AssessmentHelper;
import com.diplome.businessassessment.model.QuestionModelForView;
import com.diplome.businessassessment.service.AssessmentService;
import com.diplome.businessassessment.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class AssessmentController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private AssessmentHelper assessmentHelper;

    @RequestMapping("/assessment")
    public String getAssessment(Model model) {
        List<QuestionModelForView> questions = questionService.getQuestions();
        model.addAttribute("questions", questions);
        return "assessment";
    }

    @PostMapping("/assessment-result")
    public String getAssessmentResult(@RequestBody Map<String, String> answerMap, Model model) {
        model.addAttribute("results", assessmentService.makeAssessment(answerMap));
        return "assessment-result";
    }

}
