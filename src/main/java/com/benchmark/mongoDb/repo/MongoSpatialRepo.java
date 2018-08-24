package com.benchmark.mongoDb.repo;

import com.benchmark.mongoDb.domain.MongoSpatialShape;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoSpatialRepo extends MongoRepository<MongoSpatialShape, Long>, MongoInterface<MongoSpatialShape> {
    List<MongoSpatialShape> findByMultiPointNear(Point p, Distance d);
}
