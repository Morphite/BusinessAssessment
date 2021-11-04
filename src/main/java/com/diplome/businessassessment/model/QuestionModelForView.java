package com.diplome.businessassessment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionModelForView {

    private String id;
    private String question;
    private List<? extends AbstractAnswer> answers;
}
