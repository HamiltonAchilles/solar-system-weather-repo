package com.meli.forecasts.weather.dto;

public enum PlanetEnum {

    FERENGI(1, 500*1000, -1),
    BETASOIDE(3, 2000*1000, -1),
    VULCANO(5, 1000*1000, 1);

    private final Integer angularSpeedInDegrees;
    private final Integer distanceInMeters;
    private final Integer clockwise;

    PlanetEnum(Integer angularSpeedInDegrees, Integer distanceInMeters, Integer clockwise) {
        this.angularSpeedInDegrees = angularSpeedInDegrees;
        this.distanceInMeters = distanceInMeters;
        this.clockwise = clockwise;
    }

    public Integer getAngularSpeedInDegrees() {
        return angularSpeedInDegrees;
    }

    public Integer getDistanceInMeters() {
        return distanceInMeters;
    }

    public Integer getClockwise() {
        return clockwise;
    }
}
