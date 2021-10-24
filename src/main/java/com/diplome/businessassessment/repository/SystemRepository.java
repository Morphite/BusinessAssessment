package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.System;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends MongoRepository<System, String> {
}
