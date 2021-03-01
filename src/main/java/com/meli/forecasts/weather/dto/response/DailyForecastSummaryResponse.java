package com.meli.forecasts.weather.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.forecasts.weather.dto.WeatherEnum;

public class DailyForecastSummaryResponse {

    @JsonProperty("dia")
    private int day;
    @JsonProperty("pico_lluvia")
    private boolean peakRainyDay;
    @JsonIgnore
    private int season;
    @JsonIgnore
    private WeatherEnum weather;

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

    public boolean isPeakRainyDay() {
        return peakRainyDay;
    }

    public void setPeakRainyDay(boolean peakRainyDay) {
        this.peakRainyDay = peakRainyDay;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

}
