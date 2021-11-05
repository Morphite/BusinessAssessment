package com.diplome.businessassessment.controller;

import com.diplome.businessassessment.helper.AssessmentHelper;
import com.diplome.businessassessment.model.Answer;
import com.diplome.businessassessment.model.QuestionModel;
import com.diplome.businessassessment.model.QuestionModelForView;
import com.diplome.businessassessment.repository.AnswerRepository;
import com.diplome.businessassessment.repository.QuestionRepository;
import com.diplome.businessassessment.service.AssessmentService;
import com.diplome.businessassessment.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
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

    @RequestMapping("/test")
    public String createQuestion() {

//        Map<String, Double> assess = assessmentHelper.assess(1,
//                Map.of("75393533-15b7-4afa-8e99-3ec5be04f4ef", 1.5,
//                        "d44a9c9c-8098-4a30-8c0a-e8e694d71d27", 0.3,
//                        "9eaac9f3-e8c6-4ac0-b276-8f8b007b1bcc", 1.5,
//                        "01208bfb-ef21-4b4a-8424-68e229668f79", 0.3,
//                        "98a76a9f-a1e2-404c-9db2-c545ea8c3ee1", 0.0,
//                        "df10ba48-c3a1-4a44-b658-c7dd94616375", 0.0,
//                        "cac1eb95-5ac2-4aed-a2f4-469d28260f02", 0.0,
//                        "787aebfd-f39d-4c0e-beac-f3cca2eebbe3", 0.0),
//
//                Map.of("3fe661bd-211d-46ae-8712-e2f30587876e", true,
//                        "8fc809b6-e92e-40a3-b1e9-05edf1bdc92e", false,
//                        "2fb357ad-a2de-4aac-b344-19aa1f02adeb", true,
//                        "1828110e-45a2-4dc7-ac04-337d1f6cfe7f", true));
//        System.out.println(assess);



        return "assessment";
    }

    @PostMapping("/assessment-result")
    public String getAssessmentResult(@RequestBody Map<String, String> answerMap, Model model) {
        model.addAttribute("results", assessmentService.makeAssessment(answerMap));
        return "assessment-result";
    }

}
