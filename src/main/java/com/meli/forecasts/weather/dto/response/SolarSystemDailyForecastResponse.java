package com.meli.forecasts.weather.dto.response;

import com.meli.forecasts.weather.dto.WeatherEnum;

public class SolarSystemDailyForecastResponse {

    private int day;
    private WeatherEnum weather;
    private String message;

    public SolarSystemDailyForecastResponse(int day, WeatherEnum weather, String message) {
        this.day = day;
        this.weather = weather;
        this.message = message;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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
