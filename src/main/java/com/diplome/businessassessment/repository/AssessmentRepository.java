package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.Assessment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssessmentRepository  extends MongoRepository<Assessment, String> {
}
