package com.benchmark.model;

import java.util.List;

public class CoordinateGetResponse {
    private double mySQL;
    private double postGIS;
    private double neo4j;
    private double neo4jSpatial;
    private double MongoDb;
    private double mongoSpatial;
    private List<Coordinate> coordinates;

    public double getNeo4jSpatial() {
        return neo4jSpatial;
    }

    public void setNeo4jSpatial(double neo4jSpatial) {
        this.neo4jSpatial = neo4jSpatial;
    }

    public double getMongoSpatial() {
        return mongoSpatial;
    }

    public void setMongoSpatial(double mongoSpatial) {
        this.mongoSpatial = mongoSpatial;
    }

    public double getMySQL() {
        return mySQL;
    }

    public void setMySQL(double mySQL) {
        this.mySQL = mySQL;
    }

    public double getPostGIS() {
        return postGIS;
    }

    public void setPostGIS(double postGIS) {
        this.postGIS = postGIS;
    }

    public double getNeo4j() {
        return neo4j;
    }

    public void setNeo4j(double neo4j) {
        this.neo4j = neo4j;
    }

    public double getMongoDb() {
        return MongoDb;
    }

    public void setMongoDb(double mongoDb) {
        MongoDb = mongoDb;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
