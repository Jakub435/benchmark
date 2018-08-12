package com.benchmark.service;

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

        String wktString = "MULTIPOINT (";

        int size = coordinates.size();
        for(int i = 0; i < size; i++){
            String lat = Double.toString(coordinates.get(i).getLat());
            String lng = Double.toString(coordinates.get(i).getLng());

            if(i<size-1) {
                wktString += lat + " " + lng + ", ";
            } else {
                wktString += lat + " " + lng + ")";
            }


        }

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