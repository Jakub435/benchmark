package com.benchmark.Neo4jSpatial;

import com.benchmark.CoordinateToMultiPoint;
import com.benchmark.model.CoordinateWrapper;
import org.neo4j.driver.v1.*;

public class Neo4jSpatial implements AutoCloseable{
    private final Driver driver;

    public Neo4jSpatial(){
        driver = GraphDatabase.driver("bolt://192.168.99.100:7687");
    }

    public void deleteAndCreateMultiPointLayer(){
        execute("MATCH (n)\n" +
                "DETACH DELETE n");
        execute("CALL spatial.addWKTLayer('multiPoint', \"MultiPoint\");");
    }

    public double getReadTime(String name){
        String statement = prepareReadStatement(name);
        long before = System.currentTimeMillis();
        execute(statement);
        long after = System.currentTimeMillis();
        double time = (double)(after-before);

        return time;
    }

    public double getSaveTime(String name, CoordinateWrapper coordinates){
        String statement = prepareSaveStatement(name, coordinates);
        long before = System.currentTimeMillis();
        execute(statement);
        long after = System.currentTimeMillis();
        double time = (double)(after-before);

        return time;
    }

    private StatementResult execute(String statement){
        try(Session session = driver.session()) {
            return session.run(statement);
        }
    }

    private String prepareReadStatement(String name){
        return "MATCH ({name:\"" + name + "\"})-[:COORDINATE]->(coord)\n" +
                "RETURN coord";
    }
    private String prepareSaveStatement(String name, CoordinateWrapper coordinates){
        CoordinateToMultiPoint coordinateToMultiPoint = new CoordinateToMultiPoint();

        String multiPoint = coordinateToMultiPoint.getWKTMultiPoint(coordinates);
        return  "CREATE(shape:Shape{name:'" + name + "'})\n" +
                "WITH shape\n" +
                "CALL spatial.addWKT('multiPoint', '" + multiPoint + "') YIELD node\n" +
                "CREATE (shape)-[:COORDINATE]->(node)";
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

}