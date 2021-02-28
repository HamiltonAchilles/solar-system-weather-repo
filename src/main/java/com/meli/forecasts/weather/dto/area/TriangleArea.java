package com.meli.forecasts.weather.dto.area;

import com.meli.forecasts.weather.dto.coordinate.cartesian.CartesianCoordinate;

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

    public int[] getXAxis() {
        int xA = (int)Math.ceil(Math.abs(coordinateA.getX()));
        int xB = (int)Math.ceil(Math.abs(coordinateB.getX()));
        int xC = (int)Math.ceil(Math.abs(coordinateC.getX()));

        if(coordinateA.getX() < 0){
            xA = xA * -1;
        }
        if(coordinateB.getX() < 0){
            xB = xB * -1;
        }
        if(coordinateC.getX() < 0){
            xC = xC * -1;
        }
        return new int[]{xA, xB, xC};
    }

    public int[] getYAxis() {
        int yA = (int)Math.ceil(Math.abs(coordinateA.getY()));
        int yB = (int)Math.ceil(Math.abs(coordinateB.getY()));
        int yC = (int)Math.ceil(Math.abs(coordinateC.getY()));

        if(coordinateA.getY() < 0){
            yA = yA * -1;
        }
        if(coordinateB.getY() < 0){
            yB = yB * -1;
        }
        if(coordinateC.getY() < 0){
            yC = yC * -1;
        }
        return new int[]{yA, yB, yC};
    }

}
