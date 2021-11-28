package com.diplome.businessassessment.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "answer")
public class Answer extends AbstractAnswer {

    private double value;

    public Answer() {
    }

    public Answer(String answer, double value) {
        super(answer);
        this.value = value;
    }
}


