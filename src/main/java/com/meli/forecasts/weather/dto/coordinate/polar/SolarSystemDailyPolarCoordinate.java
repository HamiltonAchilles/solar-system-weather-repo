package com.meli.forecasts.weather.dto.coordinate.polar;

import static com.meli.forecasts.weather.dto.PlanetEnum.BETASOIDE;
import static com.meli.forecasts.weather.dto.PlanetEnum.FERENGI;
import static com.meli.forecasts.weather.dto.PlanetEnum.VULCANO;

public class SolarSystemDailyPolarCoordinate {

    private int day;
    private FerengiPolarCoordinate ferengiPolarCoordinate;
    private BetasoidePolarCoordinate betasoidePolarCoordinate;
    private VulcanoPolarCoordinate vulcanoPolarCoordinate;

    public SolarSystemDailyPolarCoordinate(int day) {
        this.day = day;
        this.ferengiPolarCoordinate = new FerengiPolarCoordinate(day * FERENGI.getDegreesPerDay());
        this.betasoidePolarCoordinate = new BetasoidePolarCoordinate(day * BETASOIDE.getDegreesPerDay());
        this.vulcanoPolarCoordinate = new VulcanoPolarCoordinate(day * VULCANO.getDegreesPerDay());
    }

    public FerengiPolarCoordinate getFerengiPolarCoordinate() {
        return ferengiPolarCoordinate;
    }

    public BetasoidePolarCoordinate getBetasoidePolarCoordinate() {
        return betasoidePolarCoordinate;
    }

    public VulcanoPolarCoordinate getVulcanoPolarCoordinate() {
        return vulcanoPolarCoordinate;
    }

    public int getDay() {
        return day;
    }

}
