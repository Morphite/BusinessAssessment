package com.diplome.businessassessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "functionality")
public class FunctionalityModel {

    @Id
    private String id;

    private String question;

    public FunctionalityModel() {
        this.id = UUID.randomUUID().toString();
    }

    public FunctionalityModel(String question) {
        this.id = UUID.randomUUID().toString();
        this.question = question;
    }
}
