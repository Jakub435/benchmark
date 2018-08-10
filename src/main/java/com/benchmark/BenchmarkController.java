package com.benchmark;

import com.benchmark.model.*;
import com.benchmark.service.MongoService;
import com.benchmark.service.MySqlService;
import com.benchmark.service.Neo4jService;
import com.benchmark.service.PostgreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
public class BenchmarkController {
    @Autowired
    private MySqlService mySqlService;

    @Autowired
    private PostgreService postgreService;

    @Autowired
    private Neo4jService neo4jService;

    @Autowired
    private MongoService mongoService;

    @PostConstruct
    public void clearAllNoSqlDb(){
        neo4jService.clear();
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

        response.setMySQL(mySqlService.getReadTime(shapeName));
        response.setNeo4j(neo4jService.getReadTime(shapeName));
        response.setPostGIS(postgreService.getReadTime(shapeName));

        return response;
    }

    @PostMapping(path = "/coordinate/{shapeName}", consumes="application/json",produces="application/json")
    public @ResponseBody
    CoordinatePostResponse postShapeCoordinate(@PathVariable String shapeName, @RequestBody CoordinateWrapper coordinate){
        shapeName = mySqlService.checkIfExistAndReturnNewName(shapeName);

        CoordinatePostResponse response = mySqlService.getSaveTime(shapeName, coordinate);

        response.setMongoDb(mongoService.getSaveTime(shapeName, coordinate));
        response.setNeo4j(neo4jService.getSaveTime(shapeName, coordinate));
        response.setPostGIS(postgreService.getSaveTime(shapeName, coordinate));

        return response;
    }

}
