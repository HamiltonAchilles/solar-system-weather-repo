package com.meli.forecasts.weather.model.area;

public class QuadrilateralArea extends Area {

    private TriangleArea triangleAreaOne;
    private TriangleArea triangleAreaTwo;

    public QuadrilateralArea(TriangleArea triangleOne, TriangleArea triangleTwo) {
        this.triangleAreaOne = triangleOne;
        this.triangleAreaTwo = triangleTwo;
    }

    public double getArea() {
        return (triangleAreaOne.getArea() / 2) + (triangleAreaTwo.getArea() / 2);
    }

}
