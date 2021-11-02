package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.QuestionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionModel, String> {
}
