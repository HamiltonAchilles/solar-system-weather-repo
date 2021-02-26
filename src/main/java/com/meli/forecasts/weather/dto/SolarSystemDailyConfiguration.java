package com.meli.forecasts.weather.dto;

import com.meli.forecasts.weather.model.SolarSystemDailyForecast;

import static com.meli.forecasts.weather.dto.WeatherEnum.CONDICIONES_NORMALES;
import static com.meli.forecasts.weather.dto.WeatherEnum.CONDICIONES_OPTIMAS;
import static com.meli.forecasts.weather.dto.WeatherEnum.LLUVIA;
import static com.meli.forecasts.weather.dto.WeatherEnum.SEQUIA;
import static com.meli.forecasts.weather.dto.WeatherEnum.UNKNOWN;

public class SolarSystemDailyConfiguration {

    private int day;
    private boolean sunInside;
    private boolean planetsAligned;
    private double trianglePerimeter;

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

    public double getTrianglePerimeter() {
        return trianglePerimeter;
    }

    public void setTrianglePerimeter(double trianglePerimeter) {
        this.trianglePerimeter = trianglePerimeter;
    }

    public SolarSystemDailyForecast toForecast(){
        SolarSystemDailyForecast forecast = new SolarSystemDailyForecast();
        forecast.setDay(getDay());
        forecast.setWeather(calculateWeather());
        return forecast;
    }

    private WeatherEnum calculateWeather() {
        if(isPlanetsAligned() && isSunInside()){
            return SEQUIA;
        }
        if(!isPlanetsAligned() && isSunInside()){
            return LLUVIA; //TODO LLUVIA_PICO_MAXIMO
        }
        if(!isPlanetsAligned() && !isSunInside()){
            return CONDICIONES_NORMALES;
        }
        if(isPlanetsAligned() && !isSunInside()){
            return CONDICIONES_OPTIMAS;
        }
        return UNKNOWN;
    }

}
