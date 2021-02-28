package com.meli.forecasts.weather.dto;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public enum PlanetEnum {

    FERENGI(1, 500, TRUE),
    BETASOIDE(3, 2000, TRUE),
    VULCANO(-5, 1000, FALSE);

    private final Integer degreesPerDay;
    private final Integer radiusInMeters;
    private final Boolean clockwise;

    PlanetEnum(Integer degreesPerDay, Integer radiusInMeters, Boolean clockwise) {
        this.degreesPerDay = degreesPerDay;
        this.radiusInMeters = radiusInMeters;
        this.clockwise = clockwise;
    }

    public Integer getDegreesPerDay() {
        return degreesPerDay;
    }

    public Integer getRadiusInMeters() {
        return radiusInMeters;
    }

    public Boolean getClockwise() {
        return clockwise;
    }
}
