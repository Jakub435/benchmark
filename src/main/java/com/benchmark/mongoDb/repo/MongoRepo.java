package com.benchmark.mongoDb.repo;

import com.benchmark.mongoDb.domain.MongoShape;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRepo extends MongoRepository<MongoShape, Long> {
    MongoShape findFirstByName(String name);
}