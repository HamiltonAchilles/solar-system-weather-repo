package com.meli.forecasts.weather.model.area;

import com.meli.forecasts.weather.model.coordinate.cartesian.CartesianCoordinate;

public class TriangleArea {

    private CartesianCoordinate coordinateA;
    private CartesianCoordinate coordinateB;
    private CartesianCoordinate coordinateC;

    public TriangleArea(
            CartesianCoordinate coordinateA,
            CartesianCoordinate coordinateB,
            CartesianCoordinate coordinateC) {
        this.coordinateA = coordinateA;
        this.coordinateB = coordinateB;
        this.coordinateC = coordinateC;
    }

    public CartesianCoordinate getCoordinateA() {
        return coordinateA;
    }

    public CartesianCoordinate getCoordinateB() {
        return coordinateB;
    }

    public CartesianCoordinate getCoordinateC() {
        return coordinateC;
    }

}
