package com.meli.forecasts.weather.dto.response;

import com.meli.forecasts.weather.dto.WeatherEnum;

public class SeasonForecastResponse {

    private int startDay;
    private int endDay;
    private int peakRainyDay;
    private WeatherEnum weather;
    private String message;

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
