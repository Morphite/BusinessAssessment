package com.diplome.businessassessment.service;

import com.diplome.businessassessment.model.Answer;
import com.diplome.businessassessment.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public Optional<Answer> findById(String answerId) {
        return answerRepository.findById(answerId);
    }

    public void createOrUpdateAnswer(Answer answer) {
        answerRepository.save(answer);
    }
}
