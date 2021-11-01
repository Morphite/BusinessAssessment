package com.diplome.businessassessment.repository;


import com.diplome.businessassessment.model.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

import java.util.stream.Collectors;

@Repository
public interface MetricRepository extends MongoRepository<Metric, String> {

    default Map<String, Metric> findAllMetricsMapWithNameAsKey() {
        return findAll().stream().collect(Collectors.toMap(Metric::getName, abstractMetric -> abstractMetric));
    }
}
