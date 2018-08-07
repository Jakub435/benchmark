package com.benchmark.Neo4j.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Neo4jShape {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Relationship(type = "HAS_POINT_AT")
    private List<ShapeNeo> shapeNeo;


    public Neo4jShape() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShapeNeo> getShapeNeo() {
        return shapeNeo;
    }

    public void setShapeNeo(List<ShapeNeo> shapeNeo) {
        this.shapeNeo = shapeNeo;
    }
}