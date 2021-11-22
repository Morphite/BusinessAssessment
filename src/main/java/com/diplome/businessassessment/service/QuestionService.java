package com.diplome.businessassessment.service;

import com.diplome.businessassessment.model.*;
import com.diplome.businessassessment.repository.AnswerRepository;
import com.diplome.businessassessment.repository.MetricRepository;
import com.diplome.businessassessment.repository.FunctionalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private FunctionalityRepository functionalityRepository;

    @Autowired
    private MetricRepository metricRepository;

    public List<QuestionModelForView> getQuestions() {
        List<Metric> metricsQuestions = metricRepository.findAll();
        List<FunctionalityModel> rawYesNoQuestions = functionalityRepository.findAll();

        List<Answer> answersForMetric = answerRepository.findAll();
        answersForMetric.sort(Comparator.comparing(Answer::getValue).reversed());

        List<QuestionModelForView> questionsWithAnswers = new ArrayList<>();

        // maybe add this answers in DB?
        List<Answer> answersForMarketSegment = List.of(new Answer("Micro business", 1),
                new Answer("Small business", 2),
                new Answer("Medium business", 3),
                new Answer("Large business", 4));
        questionsWithAnswers.add(new QuestionModelForView("market",
                "What's your market segment?", answersForMarketSegment));

        questionsWithAnswers.addAll(metricsQuestions.stream()
                .map(metric -> new QuestionModelForView(metric.getId(),
                        "How important is metric '" + metric.getName() + "' for you?", answersForMetric))
                .collect(Collectors.toList()));

        List<BooleanAnswer> booleanAnswers = List.of(new BooleanAnswer("Yes", true),
                new BooleanAnswer("No", false));

        List<QuestionModelForView> yesNoQuestions = rawYesNoQuestions.stream()
                .map(yesNoQuestion -> new QuestionModelForView(yesNoQuestion.getId(),
                        "Do you need " + yesNoQuestion.getQuestion() + "?", booleanAnswers)).collect(Collectors.toList());

        questionsWithAnswers.addAll(yesNoQuestions);
        return questionsWithAnswers;
    }
}

