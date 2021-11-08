package com.diplome.businessassessment.service;

import com.diplome.businessassessment.helper.AssessmentHelper;
import com.diplome.businessassessment.model.Assessment;
import com.diplome.businessassessment.model.SecurityUserDetails;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.AssessmentRepository;
import com.diplome.businessassessment.repository.QuestionRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private AssessmentRepository assessmentRepository;

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

        String userId = ((SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
        assessmentRepository.save(new Assessment(userId, systemIdScoreMap));

        return convertSystemIdScoreMapToSystemNameScoreMap(systemIdScoreMap, systems);
    }

    public Map<String, Double> convertSystemIdScoreMapToSystemNameScoreMap
            (Map<String, Double> systemIdScoreMapWithKeyAsSystemId, Map<String, System> systems){
        return systemIdScoreMapWithKeyAsSystemId.entrySet().stream()
                .collect(Collectors.toMap(entry -> systems.get(entry.getKey()).getName(), Map.Entry::getValue));
    }

}
