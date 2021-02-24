package com.meli.forecasts.weather.model.area;

import com.meli.forecasts.weather.model.coordinate.CartesianCoordinate;

public class TriangleArea extends Area {

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

    public double getArea() {
        return 0.5 * Math.abs(
                (coordinateA.getX() * coordinateB.getY()) +
                (coordinateC.getX() * coordinateA.getY()) +
                (coordinateB.getX() * coordinateC.getY()) -
                (coordinateC.getX() * coordinateB.getY()) -
                (coordinateA.getX() * coordinateC.getY()) -
                (coordinateB.getX() * coordinateA.getY())
        );
    }

}
