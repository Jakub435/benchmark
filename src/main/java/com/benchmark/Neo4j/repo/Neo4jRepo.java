package com.benchmark.Neo4j.repo;

import com.benchmark.Neo4j.domain.Neo4jShape;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Neo4jRepo extends Neo4jRepository<Neo4jShape,Long> {
    Neo4jShape findFirstByName(String name);
}
