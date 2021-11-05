package com.diplome.businessassessment.service;

import com.diplome.businessassessment.helper.AssessmentHelper;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.QuestionRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentHelper assessmentHelper;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SystemRepository systemRepository;

    public Map<String, Double> makeAssessment(Map<String, String> answerMap) {
        int marketSegmentId = Integer.parseInt(answerMap.get("market"));
        answerMap.remove("market");


        Map<String, String> yesNoQuestionMap = new HashMap<>();
        Map<String, Boolean> yesNoQuestionBooleanMap;
        Map<String, Double> modifiedAnswersMap;

        questionRepository.findAllQuestionsIds().forEach(yesNoQuestion -> {
            yesNoQuestionMap.put(yesNoQuestion, answerMap.get(yesNoQuestion));
            answerMap.remove(yesNoQuestion);
        });

        yesNoQuestionBooleanMap = yesNoQuestionMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> Boolean.parseBoolean(v.getValue())));

        modifiedAnswersMap = answerMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> Double.parseDouble(v.getValue())));
        modifiedAnswersMap.keySet().forEach(answerMap::remove);

        Map<String, System> systems = systemRepository.findAllSystemMapWithIdAsKey();
        Map<String, Double> systemIdScoreMap = assessmentHelper.assess(marketSegmentId, modifiedAnswersMap, yesNoQuestionBooleanMap);
        Map<String, Double> systemIdScoreMapWithKeyAsSystemName = new HashMap<>();
        systemIdScoreMap.forEach((k, v) -> systemIdScoreMapWithKeyAsSystemName.put(systems.get(k).getName(), v));
        return systemIdScoreMapWithKeyAsSystemName;
    }
}
