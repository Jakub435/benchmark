package com.benchmark.mongoDb.repo;

import com.benchmark.mongoDb.domain.MongoSpatialShape;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoSpatialRepo extends MongoRepository<MongoSpatialShape, Long>, MongoInterface<MongoSpatialShape> {
}
