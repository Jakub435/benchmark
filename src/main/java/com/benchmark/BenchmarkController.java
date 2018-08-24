package com.benchmark;

import com.benchmark.Neo4jSpatial.Neo4jSpatial;
import com.benchmark.model.*;
import com.benchmark.service.MongoService;
import com.benchmark.service.MySqlService;
import com.benchmark.service.Neo4jService;
import com.benchmark.service.PostgreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
public class BenchmarkController {
    @Autowired
    @Qualifier(value = "mainService")
    private MySqlService mySqlService;

    @Autowired
    @Qualifier(value = "postgresqlService")
    private PostgreService postgreService;

    @Autowired
    private Neo4jService neo4jService;

    @Autowired
    private MongoService mongoService;

    private Neo4jSpatial neo4jSpatial = new Neo4jSpatial();

    @PostConstruct
    public void clearAll(){
        neo4jSpatial.deleteAndCreateMultiPointLayer();
        mongoService.clear();
    }

    @GetMapping(path = "/coordinateName")
    public @ResponseBody
    List<NameResponse> getAllName(){
        return mySqlService.getAllName();
    }

    @GetMapping(path = "/coordinate/{shapeName}")
    public @ResponseBody
    CoordinateGetResponse getShapeCoordinate(@PathVariable String shapeName)  {
        CoordinateGetResponse response = mongoService.getReadTime(shapeName);

        response.setMongoSpatial(mongoService.getSpatialReadTime(shapeName));
        response.setMySQL(mySqlService.getReadTime(shapeName));
        response.setNeo4j(neo4jService.getReadTime(shapeName));
        response.setNeo4jSpatial(neo4jSpatial.getReadTime(shapeName));
        response.setPostGIS(postgreService.getReadTime(shapeName));

        return response;
    }

    @PostMapping(path = "/coordinate/{shapeName}", consumes="application/json",produces="application/json")
    public @ResponseBody
    CoordinatePostResponse postShapeCoordinate(@PathVariable String shapeName, @RequestBody CoordinateWrapper coordinate){
        shapeName = mySqlService.checkIfExistAndReturnNewName(shapeName);

        CoordinatePostResponse response = mySqlService.getSaveTime(shapeName, coordinate);

        response.setMongoDb(mongoService.getSaveTime(shapeName, coordinate));
        response.setMongoSpatial(mongoService.getSpatialSaveTime(shapeName, coordinate));
        response.setNeo4j(neo4jService.getSaveTime(shapeName, coordinate));
        response.setNeo4jSpatial(neo4jSpatial.getSaveTime(shapeName, coordinate));
        response.setPostGIS(postgreService.getSaveTime(shapeName, coordinate));

        return response;
    }

}
