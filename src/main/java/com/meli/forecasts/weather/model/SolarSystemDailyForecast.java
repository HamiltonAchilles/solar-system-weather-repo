package com.meli.forecasts.weather.model;

import com.meli.forecasts.weather.dto.WeatherEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forecast")
public class SolarSystemDailyForecast {

    @Id
    private int day;

    @Enumerated(EnumType.STRING)
    @Column(name = "weather")
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

}
