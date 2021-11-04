package com.diplome.businessassessment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class AbstractAnswer {

    private String id;
    private String answer;

    public AbstractAnswer(String answer) {
        this.id = UUID.randomUUID().toString();
        this.answer = answer;
    }
}
