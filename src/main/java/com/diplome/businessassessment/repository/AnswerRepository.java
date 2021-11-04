package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerRepository extends MongoRepository<Answer, String> {
}
