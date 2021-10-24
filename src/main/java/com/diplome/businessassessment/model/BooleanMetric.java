package com.diplome.businessassessment.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "metric")
public class BooleanMetric extends AbstractMetric {

    private boolean value;

    public BooleanMetric(String name, boolean value) {
        super(name);
        this.value = value;
    }
}
