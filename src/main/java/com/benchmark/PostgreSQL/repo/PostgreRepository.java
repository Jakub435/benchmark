package com.benchmark.PostgreSQL.repo;

import com.benchmark.PostgreSQL.domain.PostgreShape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("postgresqlRepositry")
public interface PostgreRepository extends JpaRepository<PostgreShape, Integer> {
    PostgreShape findFirstByName(String name);
}
