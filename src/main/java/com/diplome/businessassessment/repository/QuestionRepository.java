package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.FunctionalityModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface QuestionRepository extends MongoRepository<FunctionalityModel, String> {

    default List<String> findAllQuestionsIds() {
        return findAll().stream().map(FunctionalityModel::getId).collect(Collectors.toList());
    }
}
