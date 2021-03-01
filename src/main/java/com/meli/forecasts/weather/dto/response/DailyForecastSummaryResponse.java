package com.meli.forecasts.weather.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.forecasts.weather.dto.WeatherEnum;

public class DailyForecastResponse {

    @JsonProperty("dia")
    private int day;
    @JsonProperty("clima")
    private WeatherEnum weather;

    public DailyForecastResponse() {
    }

    public DailyForecastResponse(int day, WeatherEnum weather) {
        this.day = day;
        this.weather = weather;
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

}
