package com.diplome.businessassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@Document(collection = "question")
public class Question {

    private String id;
    private String question;

    public Question() {
        this.id = UUID.randomUUID().toString();
    }
}
