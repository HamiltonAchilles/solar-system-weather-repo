package com.meli.forecasts.weather.dto;

public enum PlanetEnum {

    FERENGI(1, 500*1000, -1),
    BETASOIDE(3, 2000*1000, -1),
    VULCANO(5, 1000*1000, 1);

    private final Integer degreesPerDay;
    private final Integer radiusInMeters;
    private final Integer clockwise;

    PlanetEnum(Integer degreesPerDay, Integer radiusInMeters, Integer clockwise) {
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

    public Integer getClockwise() {
        return clockwise;
    }
}
