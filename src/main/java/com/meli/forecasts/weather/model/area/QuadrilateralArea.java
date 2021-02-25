package com.meli.forecasts.weather.model.area;

public class QuadrilateralArea {

    private TriangleArea triangleAreaOne;
    private TriangleArea triangleAreaTwo;

    public QuadrilateralArea(TriangleArea triangleOne, TriangleArea triangleTwo) {
        this.triangleAreaOne = triangleOne;
        this.triangleAreaTwo = triangleTwo;
    }

    public TriangleArea getTriangleAreaOne() {
        return triangleAreaOne;
    }

    public TriangleArea getTriangleAreaTwo() {
        return triangleAreaTwo;
    }

}
