package com.benchmark.MySQL.domain;

import javax.persistence.*;

@Entity
@Table(name = "shape")
public class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "shape_id")
    private Integer shapeId;
    private Double lat;
    private Double lng;
    private Integer priority;

    public Shape(Integer shapeId, Double lat, Double lng, Integer priority) {
        this.shapeId = shapeId;
        this.lat = lat;
        this.lng = lng;
        this.priority = priority;
    }

    public Shape(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShapeId() {
        return shapeId;
    }

    public void setShapeId(Integer shapeId) {
        this.shapeId = shapeId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}