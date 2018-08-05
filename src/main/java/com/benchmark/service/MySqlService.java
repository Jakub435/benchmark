package com.benchmark.service;

import com.benchmark.MySQL.domain.Shape;
import com.benchmark.MySQL.domain.ShapeName;
import com.benchmark.MySQL.repo.ShapeNameRepository;
import com.benchmark.MySQL.repo.ShapeRepository;
import com.benchmark.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MySqlService {
    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ShapeNameRepository shapeNameRepository;

    public CoordinatePostResponse createResponseForSave(String name,CoordinateWrapper coordinate){
        List<Shape> allShape;
        CoordinatePostResponse response = new CoordinatePostResponse();

        if(shapeNameRepository.existsByName(name)){
            name = name + System.currentTimeMillis();
        }
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

    public CoordinateGetResponse createResponseForRead(String shapeName){
        int shapeId = shapeNameRepository.getId(shapeName);

        long before = System.currentTimeMillis();
        List<Shape> shape = shapeRepository.findAllByShapeIdOrderByPriority(shapeId);
        List<Coordinate> coordinate = getCoordinateListFromShape(shape);
        long after = System.currentTimeMillis();
        double time = (double)(after-before);
        CoordinateGetResponse response = new CoordinateGetResponse();

        response.setCoordinates(coordinate);
        response.setMySQL(time);

        return response;
    }

    private List<Coordinate> getCoordinateListFromShape(List<Shape> list){
        List<Coordinate> coordinate = new ArrayList<>();

        for (Shape singleShape: list){
            double lat = singleShape.getLat();
            double lng = singleShape.getLng();

            coordinate.add(new Coordinate(lat,lng));
        }

        return coordinate;
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
