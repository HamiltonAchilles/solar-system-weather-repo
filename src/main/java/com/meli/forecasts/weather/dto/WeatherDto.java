package com.meli.forecasts.weather.dto;

public class WeatherDto {

    private Integer day;
    private WeatherEnum weather;

    public WeatherDto(Integer day, WeatherEnum weather) {
        this.day = day;
        this.weather = weather;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public WeatherEnum getWeather() {
        return weather;
    }

    public void setWeather(WeatherEnum weather) {
        this.weather = weather;
    }

}
