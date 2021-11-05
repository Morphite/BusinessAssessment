package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.System;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface SystemRepository extends MongoRepository<System, String> {
    default Map<String, System> findAllSystemMapWithIdAsKey() {
        return findAll().stream().collect(Collectors.toMap(System::getId, system -> system));
    }
}
