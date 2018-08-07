package com.benchmark.Neo4j.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class ShapeNeo {
    @Id
    @GeneratedValue
    private Long id;
    private double lat;
    private double lng;
    private int order;

    public ShapeNeo(double lat, double lng, int order) {
        this.lat = lat;
        this.lng = lng;
    }
    public ShapeNeo() {
    }
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
