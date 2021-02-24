package com.meli.forecasts.weather.model.coordinate;

public class SolarSystemDailyCartesianCoordinate {

    private Integer day;

    private VulcanoCartesianCoordinate vulcanoCoordinate;
    private FerengiCartesianCoordinate ferengiCoordinate;
    private BetasoideCartesianCoordinate betasoideCoordinate;
    private boolean sunInside;
    private boolean alignedPlanets;

    public boolean isSunInside() {
        return sunInside;
    }

    public void setSunInside(boolean sunInside) {
        this.sunInside = sunInside;
    }

    public boolean isAlignedPlanets() {
        return alignedPlanets;
    }

    public void setAlignedPlanets(boolean alignedPlanets) {
        this.alignedPlanets = alignedPlanets;
    }

    public SolarSystemDailyCartesianCoordinate(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public VulcanoCartesianCoordinate getVulcanoCoordinate() {
        return vulcanoCoordinate;
    }

    public void setVulcanoCoordinate(VulcanoCartesianCoordinate vulcanoCoordinate) {
        this.vulcanoCoordinate = vulcanoCoordinate;
    }

    public FerengiCartesianCoordinate getFerengiCoordinate() {
        return ferengiCoordinate;
    }

    public void setFerengiCoordinate(FerengiCartesianCoordinate ferengiCoordinate) {
        this.ferengiCoordinate = ferengiCoordinate;
    }

    public BetasoideCartesianCoordinate getBetasoideCoordinate() {
        return betasoideCoordinate;
    }

    public void setBetasoideCoordinate(BetasoideCartesianCoordinate betasoideCoordinate) {
        this.betasoideCoordinate = betasoideCoordinate;
    }

}
