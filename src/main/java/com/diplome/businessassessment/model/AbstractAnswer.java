package com.diplome.businessassessment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public abstract class AbstractAnswer {

    @Id
    private String id;

    private String answer;

    public AbstractAnswer() {
        this.id = UUID.randomUUID().toString();
    }

    public AbstractAnswer(String answer) {
        this.id = UUID.randomUUID().toString();
        this.answer = answer;
    }
}
