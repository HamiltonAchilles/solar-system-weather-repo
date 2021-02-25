package com.meli.forecasts.weather.dto;

import static com.meli.forecasts.weather.service.SolarSystemDailyForecastService.CLOCKWISE;
import static com.meli.forecasts.weather.service.SolarSystemDailyForecastService.COUNTERWISE;

public enum PlanetEnum {

    FERENGI(1, 500*1000, CLOCKWISE),
    BETASOIDE(3, 2000*1000, CLOCKWISE),
    VULCANO(5, 1000*1000, COUNTERWISE);

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
