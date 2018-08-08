package com.benchmark.service;

import com.benchmark.Neo4j.domain.Neo4jShape;
import com.benchmark.Neo4j.domain.ShapeNeo;
import com.benchmark.Neo4j.repo.Neo4jCoordRepo;
import com.benchmark.Neo4j.repo.Neo4jRepo;
import com.benchmark.model.CoordinateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class Neo4jService {
    @Autowired
    private Neo4jRepo neo4jRepo;
    @Autowired
    private Neo4jCoordRepo neo4jCoordRepo;

    public void clear(){
        neo4jRepo.deleteAll();
        neo4jCoordRepo.deleteAll();
    }

    public double getSaveTime(String name, CoordinateWrapper coordinates){
        Neo4jShape neo4jShape = new Neo4jShape();
        neo4jShape.setName(name);
        int size = coordinates.size();
        List<ShapeNeo> list = new ArrayList<ShapeNeo>();
        for (int j = 0; j<size;j++){
            double lat = coordinates.get(j).getLat();
            double lng = coordinates.get(j).getLng();
            list.add(new ShapeNeo(lat, lng, j));
        }
        neo4jShape.setShapeNeo(list);

        long before = System.currentTimeMillis();
        neo4jRepo.save(neo4jShape);
        long after = System.currentTimeMillis();

        double time = (double)(after-before);

        return time;
    }

    public double getReadTime(String name){
        long before = System.currentTimeMillis();
        neo4jRepo.findFirstByName(name).getShapeNeo().get(0).getLat();
        long after = System.currentTimeMillis();

        double time = (double)(after-before);

        return time;
    }

}
