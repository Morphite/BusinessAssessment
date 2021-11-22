package com.diplome.businessassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@Document(collection = "system")
public class System {

    @Id
    private String id;

    private String name;
    private String link;
    private Map<String, Integer> systemMetrics;
    private Map<String, Boolean> functionality;

    public System() {
        this.id = UUID.randomUUID().toString();
    }
}
