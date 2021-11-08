package com.diplome.businessassessment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "assessment")
public class Assessment {

    private String id;
    private String userId;
    private LocalDate date;
    private Map<String, Double> assessmentResult;

    public Assessment(String userId,Map<String, Double> assessmentResult) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.date = LocalDate.now();
        this.assessmentResult = assessmentResult;
    }
}
