package com.meli.forecasts.weather.model;

public class SolarSystemDailyForecast {

    private int day;
    private boolean sunInside;
    private boolean planetsAligned;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isSunInside() {
        return sunInside;
    }

    public void setSunInside(boolean sunInside) {
        this.sunInside = sunInside;
    }

    public boolean isPlanetsAligned() {
        return planetsAligned;
    }

    public void setPlanetsAligned(boolean planetsAligned) {
        this.planetsAligned = planetsAligned;
    }

}
