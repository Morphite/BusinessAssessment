package com.diplome.businessassessment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "functionality")
public class FunctionalityModel {

    private String id;
    private String question;

    public FunctionalityModel(String question) {
        this.id = UUID.randomUUID().toString();
        this.question = question;
    }
}
