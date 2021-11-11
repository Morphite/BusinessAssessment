package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.FunctionalityModel;
import com.diplome.businessassessment.model.Metric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface FunctionalityRepository extends MongoRepository<FunctionalityModel, String> {

    default List<String> findAllQuestionsIds() {
        return findAll().stream().map(FunctionalityModel::getId).collect(Collectors.toList());
    }

    default Map<String, FunctionalityModel> findAllFunctionalitiesMapWithIdAsKey() {
        return findAll().stream().collect(Collectors.toMap(FunctionalityModel::getId, functionalityModel -> functionalityModel));
    }
}
