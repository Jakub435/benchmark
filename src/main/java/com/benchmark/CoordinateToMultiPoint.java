package com.benchmark;

import com.benchmark.model.CoordinateWrapper;

public class CoordinateToMultiPoint {
    public String getWKTMultiPoint(CoordinateWrapper coordinates){
        String multiPoint = "MULTIPOINT (";
        int size = coordinates.size();
        for(int i = 0; i < size; i++){

            String lat = Double.toString(coordinates.get(i).getLat());
            String lng = Double.toString(coordinates.get(i).getLng());

            if(i<size-1) {
                multiPoint += lat + " " + lng + ", ";
            } else {
                multiPoint += lat + " " + lng + ")";
            }
        }
        return multiPoint;
    }
}
