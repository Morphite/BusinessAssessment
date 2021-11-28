package com.diplome.businessassessment.service;

import com.diplome.businessassessment.helper.AssessmentHelper;
import com.diplome.businessassessment.model.Assessment;
import com.diplome.businessassessment.model.SecurityUserDetails;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.AssessmentRepository;
import com.diplome.businessassessment.repository.FunctionalityRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentHelper assessmentHelper;

    @Autowired
    private FunctionalityRepository functionalityRepository;

    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    public Map<System, Double> makeAssessment(Map<String, String> answerMap) {
        int marketSegmentId = Integer.parseInt(answerMap.get("market"));
        answerMap.remove("market");

        Map<String, String> yesNoQuestionMap = new HashMap<>();
        Map<String, Boolean> yesNoQuestionBooleanMap;
        Map<String, Double> modifiedAnswersMap;

        functionalityRepository.findAllQuestionsIds().forEach(yesNoQuestion -> {
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

        return sortSystemScoreMapByDescendingValues(systemIdScoreMap, systems);
    }

    public Map<System, Double> sortSystemScoreMapByDescendingValues
            (Map<String, Double> systemIdScoreMapWithKeyAsSystemId, Map<String, System> systems) {
        Map<System, Double> systemResultMap = convertSystemIdScoreMapToSystemObjScoreMap
                (systemIdScoreMapWithKeyAsSystemId, systems);

        return systemResultMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public List<Assessment> findAllAssessmentsWithSystemNameAsKey() {
        List<Assessment> assessments = assessmentRepository.findAll();
        Map<String, System> systems = systemRepository.findAllSystemMapWithIdAsKey();

        assessments.forEach(assessment -> assessment
                .setAssessmentResult(convertSystemIdScoreMapToSystemNameScoreMap(assessment.getAssessmentResult(), systems)));
        return assessments;
    }

    public Map<String, Double> convertSystemIdScoreMapToSystemNameScoreMap
            (Map<String, Double> systemIdScoreMapWithKeyAsSystemId, Map<String, System> systems) {
        return systemIdScoreMapWithKeyAsSystemId.entrySet().stream()
                .collect(Collectors.toMap(entry -> systems.get(entry.getKey()).getName(), Map.Entry::getValue));
    }

    private Map<System, Double> convertSystemIdScoreMapToSystemObjScoreMap(Map<String, Double> systemIdScoreMapWithKeyAsSystemId, Map<String, System> systems) {
        return systemIdScoreMapWithKeyAsSystemId.entrySet().stream()
                .collect(Collectors.toMap(entry -> systems.get(entry.getKey()), Map.Entry::getValue));
    }
}
