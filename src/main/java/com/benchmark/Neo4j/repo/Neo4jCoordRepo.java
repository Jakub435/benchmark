package com.benchmark.Neo4j.repo;

import com.benchmark.Neo4j.domain.Neo4jShape;
import com.benchmark.Neo4j.domain.ShapeNeo;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Neo4jCoordRepo extends Neo4jRepository<ShapeNeo,Long> {
}
