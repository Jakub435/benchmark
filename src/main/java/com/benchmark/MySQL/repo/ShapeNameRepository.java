package com.benchmark.MySQL.repo;

import com.benchmark.MySQL.domain.ShapeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShapeNameRepository extends JpaRepository<ShapeName, Long> {

    @Query(value = "SELECT id FROM bench.shapename WHERE name = ?", nativeQuery = true)
    int getId(String name);
}
