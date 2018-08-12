package com.benchmark.MySQL.repo;

import com.benchmark.MySQL.domain.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("mainRepository")
public interface ShapeRepository extends JpaRepository<Shape, Long> {
    List<Shape> findAllByShapeIdOrderByPriority(int shapeId);
}
