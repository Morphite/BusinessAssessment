package com.diplome.businessassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "question")
public class QuestionModel {

    private String id;
    private String question;

    public QuestionModel(String question) {
        this.id = UUID.randomUUID().toString();
        this.question = question;
    }
}
