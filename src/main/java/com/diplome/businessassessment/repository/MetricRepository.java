package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.AbstractMetric;
import com.diplome.businessassessment.model.Question;
import org.springframework.data.geo.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public interface MetricRepository extends MongoRepository<AbstractMetric, String> {

    default Map<String, AbstractMetric> findAllMetricsMapWithNameAsKey() {
        return findAll().stream().collect(Collectors.toMap(AbstractMetric::getName, abstractMetric -> abstractMetric));
    }
}
