package com.benchmark.service;

import com.benchmark.model.Coordinate;
import com.benchmark.model.CoordinateGetResponse;
import com.benchmark.model.CoordinateWrapper;
import com.benchmark.mongoDb.domain.MongoShape;
import com.benchmark.mongoDb.domain.MongoSpatialShape;
import com.benchmark.mongoDb.repo.MongoRepo;
import com.benchmark.mongoDb.repo.MongoSpatialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoService {
    @Autowired
    private MongoRepo mongoRepo;

    @Autowired
    private MongoSpatialRepo mongoSpatialRepo;

    public void clear(){
        mongoRepo.deleteAll();
    }

    public CoordinateGetResponse getReadTime(String name){
        CoordinateGetResponse response = new CoordinateGetResponse();

        long before = System.currentTimeMillis();

        MongoShape mongoShape = mongoRepo.findFirstByName(name);

        long after = System.currentTimeMillis();
        double time = (double)(after-before);

        response.setCoordinates(mongoShape.getCoordinates());
        response.setMongoDb(time);

        return response;
    }

    public double getSpatialReadTime(String name){

        long before = System.currentTimeMillis();
        mongoSpatialRepo.findFirstByName(name);
        long after = System.currentTimeMillis();

        double time = (double)(after-before);

        return time;
    }

    public double getSaveTime(String name, CoordinateWrapper coordinates){
        MongoShape mongoShape = new MongoShape(name,coordinates);
        long before = System.currentTimeMillis();
        mongoRepo.save(mongoShape);
        long after = System.currentTimeMillis();

        double time = (double)(after-before);
        return time;
    }

    public double getSpatialSaveTime(String name, CoordinateWrapper coordinates){
        List<Point> pointList = new ArrayList<>();

        for (Coordinate coordinate : coordinates){
            double lat = coordinate.getLat();
            double lng = coordinate.getLng();
            pointList.add(new Point(lat,lng));
        }

        MongoSpatialShape spatialShape = new MongoSpatialShape(name, new GeoJsonMultiPoint(pointList));

        long before = System.currentTimeMillis();
        mongoSpatialRepo.save(spatialShape);
        long after = System.currentTimeMillis();

        double time = (double)(after-before);

        return time;
    }
}
