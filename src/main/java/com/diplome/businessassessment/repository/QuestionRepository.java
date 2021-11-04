package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.QuestionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionModel, String> {

    default List<String> findAllQuestionsIds() {
        return findAll().stream().map(QuestionModel::getId).collect(Collectors.toList());
    }
}
