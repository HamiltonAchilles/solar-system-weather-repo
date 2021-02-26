package com.meli.forecasts.weather.dto.coordinate.polar;

public class PolarCoordinate {

    private double radians;
    private int radius;

    public PolarCoordinate(double radians, int radius) {
        this.radians = radians;
        this.radius = radius;
    }

    public double getRadians() {
        return radians;
    }

    public int getRadius() {
        return radius;
    }

}
