package com.benchmark.service;

import com.benchmark.CoordinateToMultiPoint;
import com.benchmark.PostgreSQL.domain.PostgreShape;
import com.benchmark.PostgreSQL.repo.PostgreRepository;
import com.benchmark.model.CoordinateWrapper;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("postgresqlService")
public class PostgreService {
    @Autowired
    @Qualifier(value = "postgresqlRepositry")
    PostgreRepository postgreRepository;

    public void clear(){
        postgreRepository.deleteAll();
    }

    public double getSaveTime(String name, CoordinateWrapper coordinates){
        PostgreShape postgreShape = new PostgreShape();
        postgreShape.setName(name);

        try {
            postgreShape.setMultiPoint(prepareMultiPoint(coordinates));
        }catch (Exception e){}

        long before = System.currentTimeMillis();
        postgreRepository.save(postgreShape);
        long after = System.currentTimeMillis();
        double time = (double)(after-before);

        return time;
    }

    private MultiPoint prepareMultiPoint(CoordinateWrapper coordinates) throws ParseException {
        WKTReader wktReader = new WKTReader();

        CoordinateToMultiPoint coordinateToMultiPoint = new CoordinateToMultiPoint();
        String wktString = coordinateToMultiPoint.getWKTMultiPoint(coordinates);

        return (MultiPoint)wktReader.read(wktString);
    }

    public double getReadTime(String name){
        long before = System.currentTimeMillis();
        postgreRepository.findFirstByName(name);
        long after = System.currentTimeMillis();
        double time = (double)(after-before);
        return time;
    }
}