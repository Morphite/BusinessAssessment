package com.diplome.businessassessment.service;

import com.diplome.businessassessment.model.Metric;
import com.diplome.businessassessment.model.System;
import com.diplome.businessassessment.repository.MetricRepository;
import com.diplome.businessassessment.repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MetricService {

    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private SystemRepository systemRepository;

    public void createOrUpdateMetric(Metric metric) {
        Optional<Metric> metricForSaveOrUpdate = metricRepository.findById(metric.getId());
        metricRepository.save(metric);

        if (metricForSaveOrUpdate.isEmpty()) {
            List<System> systems = systemRepository.findAll();
            systems.forEach(system -> system.getSystemMetrics().put(metric.getId(), 0));
            systemRepository.saveAll(systems);
        }
    }

    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public Optional<Metric> findById(String metricId) {
        return metricRepository.findById(metricId);
    }

    public Map<String, Metric> findAllMetricsMapWithIdAsKey() {
        return metricRepository.findAllMetricsMapWithIdAsKey();
    }

    public void deleteById(String metricId) {
        Optional<Metric> metricForDelete = metricRepository.findById(metricId);

        if (metricForDelete.isPresent()) {
            List<System> systems = systemRepository.findAll();
            systems.forEach(system -> system.getSystemMetrics().remove(metricId));

            metricRepository.deleteById(metricId);
            systemRepository.saveAll(systems);
        }
    }

    public void testMethod() {
        java.lang.System.out.println("some testing");
    }
}
