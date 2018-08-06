package com.benchmark;

import com.benchmark.model.CoordinateWrapper;
import com.benchmark.model.CoordinateGetResponse;
import com.benchmark.model.CoordinatePostResponse;
import com.benchmark.model.NameResponse;
import com.benchmark.service.MySqlService;
import com.benchmark.service.PostgreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class BenchmarkController {
    @Autowired
    private MySqlService mySqlService;

    @Autowired
    private PostgreService postgreService;

    @GetMapping(path = "/coordinateName")
    public @ResponseBody
    List<NameResponse> getAllName(){
        return mySqlService.getAllName();
    }

    @GetMapping(path = "/coordinate/{shapeName}")
    public @ResponseBody
    CoordinateGetResponse getShapeCoordinate(@PathVariable String shapeName)  {
        CoordinateGetResponse response = mySqlService.createResponseForRead(shapeName);

        int id = mySqlService.getId(shapeName);

        response.setCassandra(1.2);
        response.setMongoDb(1.2);
        response.setNeo4j(23.3);
        response.setOrientDb(23.1);
        response.setPostGIS(postgreService.getReadTime(id));

        return response;
    }

    @PostMapping(path = "/coordinate/{shapeName}", consumes="application/json",produces="application/json")
    public @ResponseBody
    CoordinatePostResponse postShapeCoordinate(@PathVariable String shapeName, @RequestBody CoordinateWrapper coordinate){
        CoordinatePostResponse response = mySqlService.createResponseForSave(shapeName, coordinate);

        response.setCassandra(1.2);
        response.setMongoDb(1.2);
        response.setNeo4j(23.3);
        response.setOrientDb(23.1);
        response.setPostGIS(postgreService.getSaveTime(shapeName, coordinate));

        return response;
    }

}
