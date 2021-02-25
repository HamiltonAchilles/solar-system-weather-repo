package com.meli.forecasts.weather.model.coordinate.cartesian;

public class SolarSystemDailyCartesianCoordinate {

    private int day;
    private CartesianCoordinate vulcanoCoordinate;
    private CartesianCoordinate ferengiCoordinate;
    private CartesianCoordinate betasoideCoordinate;

    public SolarSystemDailyCartesianCoordinate(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public CartesianCoordinate getVulcanoCoordinate() {
        return vulcanoCoordinate;
    }

    public void setVulcanoCoordinate(CartesianCoordinate vulcanoCoordinate) {
        this.vulcanoCoordinate = vulcanoCoordinate;
    }

    public CartesianCoordinate getFerengiCoordinate() {
        return ferengiCoordinate;
    }

    public void setFerengiCoordinate(CartesianCoordinate ferengiCoordinate) {
        this.ferengiCoordinate = ferengiCoordinate;
    }

    public CartesianCoordinate getBetasoideCoordinate() {
        return betasoideCoordinate;
    }

    public void setBetasoideCoordinate(CartesianCoordinate betasoideCoordinate) {
        this.betasoideCoordinate = betasoideCoordinate;
    }

}
