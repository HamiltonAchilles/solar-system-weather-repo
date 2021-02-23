package com.meli.forecasts.weather.dto;

public enum PlanetEnum {

    FERENGI(1, 500, true),
    BETASOIDE(3, 2000, true),
    VULCANO(5, 1000, false);

    private final Integer angularSpeed;
    private final Integer distanceKilometers;
    private final Boolean clockwise;

    PlanetEnum(Integer angularSpeed, Integer distanceKilometers, Boolean clockwise) {
        this.angularSpeed = angularSpeed;
        this.distanceKilometers = distanceKilometers;
        this.clockwise = clockwise;
    }
}
