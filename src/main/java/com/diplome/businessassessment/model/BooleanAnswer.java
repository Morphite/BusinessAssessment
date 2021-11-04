package com.diplome.businessassessment.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BooleanAnswer extends AbstractAnswer {

    private boolean value;

    public BooleanAnswer(String answer, boolean value) {
        super(answer);
        this.value = value;
    }
}
