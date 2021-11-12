package com.diplome.businessassessment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "metric")
public class Metric {

    private String id;
    private String name;

    private List<Double> metricValues;

    public Metric() {
        this.id = UUID.randomUUID().toString();
    }

    public Metric(String name, List<Double> metricValues) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.metricValues = metricValues;
    }
}
