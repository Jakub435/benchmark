package com.benchmark;

import com.benchmark.MySQL.domain.Shape;
import com.benchmark.MySQL.repo.ShapeNameRepository;
import com.benchmark.MySQL.repo.ShapeRepository;
import com.benchmark.response.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BenchmarkController {
    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ShapeNameRepository shapeNameRepository;


    @GetMapping(path = "/coordinate/{shapeName}")
    public @ResponseBody
    List<Shape> getAllShape(@PathVariable String shapeName) {
        int shapeId = shapeNameRepository.getId(shapeName);
        List<Shape> shape = shapeRepository.findAll();
        return shape;
    }

}
