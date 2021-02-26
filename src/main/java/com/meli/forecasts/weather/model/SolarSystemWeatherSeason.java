package com.meli.forecasts.weather.model;

import com.meli.forecasts.weather.dto.WeatherEnum;

public class SolarSystemWeatherSeason {

    private int startDay;
    private int finalDay;
    private int peakRainyDay;
    private WeatherEnum weather;

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getFinalDay() {
        return finalDay;
    }

    public void setFinalDay(int finalDay) {
        this.finalDay = finalDay;
    }

    public int getPeakRainyDay() {
        return peakRainyDay;
    }

    public void setPeakRainyDay(int peakRainyDay) {
        this.peakRainyDay = peakRainyDay;
    }

    public WeatherEnum getWeather() {
        return weather;
    }

    public void setWeather(WeatherEnum weather) {
        this.weather = weather;
    }

}
