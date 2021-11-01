package com.diplome.businessassessment.helper;

import com.diplome.businessassessment.model.Metric;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.MetricRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AssessmentHelper {

    @Autowired
    private SystemRepository systemRepository;

    @Autowired
    private MetricRepository metricRepository;

    // RetailCRM yes/no: true false true true

    public Map<String, Double> assess(int marketSegmentId, Map<String, Double> metricAnswersValues, Map<String, Boolean> functionalityAnswers) {
        List<System> systems = systemRepository.findAll();
        List<Metric> metrics = metricRepository.findAll();

        /* поменять в бд, чтобы хранить чисто метрики и их значение, а вопросы да/нет хранить
         * в отдельной таблице, чтобы не перемешивать их.
         * И также создать массивы, мапу для системы, чтобы хранила ответы на да/нет вопросы
         * */

        double sum = 0;
        Map<String, Double> metricValueMultipliedByAnswerMap = new HashMap<>();
        for (Metric metric : metrics) {
            double valueFromQuiz = metricAnswersValues.get(metric.getId());
            double temp = metric.getMetricValues().get(marketSegmentId - 1) * valueFromQuiz;

            metricValueMultipliedByAnswerMap.put(metric.getId(), temp);
            sum += temp;
        }

        if (sum != 1.0) {
            double finalSum = sum;
            metricValueMultipliedByAnswerMap.forEach((k, v) -> metricValueMultipliedByAnswerMap.put(k, v /= finalSum));
        }

        List<System> filteredSystems = systems.stream().filter(system -> {
            for (Map.Entry<String, Boolean> functionalityEntry : system.getFunctionality().entrySet()) {
                if (functionalityAnswers.get(functionalityEntry.getKey()) && !functionalityEntry.getValue()) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

        Map<String, Double> systemScoreMap = new HashMap<>();
        for (System system : filteredSystems) {
            final double[] systemScore = {0};
            system.getSystemMetrics().forEach((k, v) -> {
                Double metricValueMultipliedByAnswer = metricValueMultipliedByAnswerMap.get(k);

                systemScore[0] += v * metricValueMultipliedByAnswer;
                systemScoreMap.put(system.getId(), systemScore[0]);
            });

        }

        return systemScoreMap;
    }
}
