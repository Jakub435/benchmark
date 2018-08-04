package com.benchmark.MySQL.domain;

import javax.persistence.*;

@Entity
@Table(name = "shape")
public class Shape {

    @Id
    @Column(name = "shape_id")
    private Integer shapeId;
    private Double lat;
    private Double lng;
    private Integer order;

    public Integer getShapeId() {
        return shapeId;
    }

    public void setShapeId(Integer id) {
        this.shapeId = id;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}