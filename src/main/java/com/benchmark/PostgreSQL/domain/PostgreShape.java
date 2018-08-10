package com.benchmark.PostgreSQL.domain;

import com.vividsolutions.jts.geom.MultiPoint;

import javax.persistence.*;

@Entity
@Table(name = "shape")
public class PostgreShape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //@Column(columnDefinition = "public.geometry(geometry,4326)")
    private MultiPoint multiPoint;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultiPoint getMultiPoint() {
        return multiPoint;
    }

    public void setMultiPoint(MultiPoint multiPoint) {
        this.multiPoint = multiPoint;
    }
}
