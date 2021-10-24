package com.diplome.businessassessment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "metric")
public abstract class AbstractMetric {

    private String id;
    private String name;

    public AbstractMetric(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
