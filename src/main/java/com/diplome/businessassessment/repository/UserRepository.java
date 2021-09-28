package com.diplome.businessassessment.repository;

import com.diplome.businessassessment.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByLogin(String login);
}
