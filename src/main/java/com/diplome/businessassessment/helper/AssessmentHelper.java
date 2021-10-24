package com.diplome.businessassessment.helper;

import com.diplome.businessassessment.model.SimpleMetric;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.MetricRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssessmentHelper {

    @Autowired
    private static SystemRepository systemRepository;

    @Autowired
    private static MetricRepository metricRepository;

    public static Map<String, Double> assess(int marketSegmentId, Map<String, Integer> metricValues, Map<String, Integer> yesNo) {
        List<System> systems = systemRepository.findAll();
        List<SimpleMetric> metrics = metricRepository.findAll();

        /* поменять в бд, чтобы хранить чисто метрики и их значение, а вопросы да/нет хранить
         * в отдельной таблице, чтобы не перемешивать их
         * */

        Map<String, Double> resultSystemAssessment = new HashMap<>();

        double sum = 0;
        Map<String, Double> metricValueMultipliedByAnswerMap = new HashMap<>();
        for (SimpleMetric metric : metrics) {
            Integer valueFromQuiz = metricValues.get(metric.getName());

            double temp = 0;

            if (marketSegmentId == 1) {
                temp = metric.getK1() * valueFromQuiz;
            } else if (marketSegmentId == 2) {
                temp = metric.getK2() * valueFromQuiz;
            } else if (marketSegmentId == 3) {
                temp = metric.getK3() * valueFromQuiz;
            } else if (marketSegmentId == 4) {
                temp = metric.getK4() * valueFromQuiz;
            }
            metricValueMultipliedByAnswerMap.put(metric.getName(), temp);
            sum += temp;
        }

        if (sum != 1.0) {
            double finalSum = sum;
            metricValueMultipliedByAnswerMap.forEach((k, v) -> metricValueMultipliedByAnswerMap.put(k, v /= finalSum));
        }

        Map<String, Double> systemScoreMap = new HashMap<>();
        for (System system : systems) {

//            system.getSystemMetrics().entrySet().stream()
//                    .map(entry -> {
//                        double systemScore = 0;
//                        Integer systemMetricValue = entry.getValue();
//                        Double metricValueMultipliedByAnswer = metricValueMultipliedByAnswerMap.get(entry.getKey());
//
//                        systemScore += systemMetricValue * metricValueMultipliedByAnswer;
//                        return systemScore;
//                    });


            system.getSystemMetrics().forEach((k, v) -> {
                double systemScore = 0;
                Integer systemMetricValue = v;
                Double metricValueMultipliedByAnswer = metricValueMultipliedByAnswerMap.get(k);

                systemScore += systemMetricValue * metricValueMultipliedByAnswer;

                systemScoreMap.put(system.getId(), systemScore);
            });
        }


        // берем сегмент и тащим с бд (занести в бд их для начала)

        // проверить вопросы на да нет и сразу откинуть систему

        // начать вычисления, выбирать метрики и в соответствии с ответами считать

        return systemScoreMap;
    }
}
