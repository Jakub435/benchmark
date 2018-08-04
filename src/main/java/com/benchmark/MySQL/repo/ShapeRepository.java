package com.benchmark.MySQL.repo;

import com.benchmark.MySQL.domain.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Long> {

}
