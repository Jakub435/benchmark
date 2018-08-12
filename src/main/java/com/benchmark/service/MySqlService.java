package com.benchmark.service;

import com.benchmark.MySQL.domain.Shape;
import com.benchmark.MySQL.domain.ShapeName;
import com.benchmark.MySQL.repo.ShapeNameRepository;
import com.benchmark.MySQL.repo.ShapeRepository;
import com.benchmark.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("mainService")
public class MySqlService{
    @Autowired
    @Qualifier(value = "mainRepository")
    private ShapeRepository shapeRepository;

    @Autowired
    private ShapeNameRepository shapeNameRepository;

    public int getId(String name){
        return shapeNameRepository.getId(name);
    }

    public String checkIfExistAndReturnNewName(String name){
        if(shapeNameRepository.existsByName(name)){
            return name + System.currentTimeMillis();
        }
        return name;
    }

    public CoordinatePostResponse getSaveTime(String name,CoordinateWrapper coordinate){
        List<Shape> allShape;
        CoordinatePostResponse response = new CoordinatePostResponse();

        ShapeName shapeName = new ShapeName(name);
        int shapeId = shapeName.getId();

        allShape = createShapeList(coordinate, shapeId);

        long before = System.currentTimeMillis();
        shapeNameRepository.save(shapeName);
        shapeRepository.saveAll(allShape);
        long after = System.currentTimeMillis();
        double time = (double)(after-before);

        response.setMySQL(time);

        return response;
    }

    private List<Shape> createShapeList(CoordinateWrapper coordinate, int shapeId){
        List<Shape> list = new ArrayList<Shape>();
        int size = coordinate.size();
        for(int i = 0; i < size; i++){
            double lat = coordinate.get(i).getLat();
            double lng = coordinate.get(i).getLng();

            Shape shape = new Shape(shapeId, lat, lng, i);
            list.add(shape);
        }
        return list;
    }

    public double getReadTime(String shapeName){
        int shapeId = shapeNameRepository.getId(shapeName);

        long before = System.currentTimeMillis();
        shapeRepository.findAllByShapeIdOrderByPriority(shapeId);
        long after = System.currentTimeMillis();
        double time = (double)(after-before);

        return time;
    }

    public List<NameResponse> getAllName(){
        List<NameResponse> allName = new ArrayList<NameResponse>();
        List<ShapeName> allShapeName = shapeNameRepository.findAll();
        for(ShapeName shape: allShapeName){
            allName.add( new NameResponse( shape.getName()));
        }
        return allName;
    }
}
