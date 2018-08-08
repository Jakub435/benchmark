package com.benchmark.service;

import com.benchmark.model.CoordinateGetResponse;
import com.benchmark.model.CoordinateWrapper;
import com.benchmark.mongoDb.domain.MongoShape;
import com.benchmark.mongoDb.repo.MongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
    @Autowired
    private MongoRepo mongoRepo;

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

    public double getSaveTime(String name, CoordinateWrapper coordinates){
        MongoShape mongoShape = new MongoShape(name,coordinates);
        long before = System.currentTimeMillis();
        mongoRepo.save(mongoShape);
        long after = System.currentTimeMillis();

        double time = (double)(after-before);
        return time;
    }
}
