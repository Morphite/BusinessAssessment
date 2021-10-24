package com.diplome.businessassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "metric")
public class SimpleMetric extends AbstractMetric {

    private double k1;
    private double k2;
    private double k3;
    private double k4;

    public SimpleMetric(String name, double k1, double k2, double k3, double k4) {
        super(name);
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.k4 = k4;
    }
}