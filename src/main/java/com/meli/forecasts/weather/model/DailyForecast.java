package com.meli.forecasts.weather.model;

import com.meli.forecasts.weather.dto.WeatherEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "forecast")
public class DailyForecast {

    @Id
    private int day;
    @Enumerated(EnumType.STRING)
    @Column(name = "weather")
    private WeatherEnum weather;
    @Column(name = "season")
    private int season;
    @Transient
    private double trianglePerimeter;

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

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public double getTrianglePerimeter() {
        return trianglePerimeter;
    }

    public void setTrianglePerimeter(double trianglePerimeter) {
        this.trianglePerimeter = trianglePerimeter;
    }

}
