package com.benchmark.model;

import java.util.List;

public class CoordinateGetResponse {
    private double mySQL;
    private double postGIS;
    private double neo4j;
    private double cassandra;
    private double orientDb;
    private double MongoDb;
    private List<Coordinate> coordinates;

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

    public double getCassandra() {
        return cassandra;
    }

    public void setCassandra(double cassandra) {
        this.cassandra = cassandra;
    }

    public double getOrientDb() {
        return orientDb;
    }

    public void setOrientDb(double orientDb) {
        this.orientDb = orientDb;
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
