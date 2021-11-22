package com.diplome.businessassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document(collection = "metric")
public class Metric {

    @Id
    private String id;

    private String name;

    private List<Double> metricValues;

    public Metric() {
        this.id = UUID.randomUUID().toString();
    }
}
