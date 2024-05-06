package com.brunin.api.repository;

import com.brunin.api.model.Task;
import com.brunin.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TaskRepository extends MongoRepository<Task, String> {
}
