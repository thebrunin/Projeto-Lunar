package com.brunin.api.repository;

import com.brunin.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ login: ?0}")
    User findByLogin(String login);
}
