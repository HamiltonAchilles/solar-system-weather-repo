package com.meli.forecasts.weather.model;

import com.meli.forecasts.weather.dto.WeatherEnum;
import io.swagger.models.auth.In;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "forecast")
public class DailyForecast {

    @Id
    private Integer day;
    @Enumerated(EnumType.STRING)
    @Column(name = "weather")
    private WeatherEnum weather;
    @Column(name = "season")
    private Integer season;
    @Column(name = "triangle_perimeter")
    private Integer trianglePerimeter;
    @Column(name = "triangle_area")
    private Integer triangleArea;
    @Column(name = "degrees_vulcano")
    private Integer vulcanoDegrees;
    @Column(name = "degrees_betasoide")
    private Integer betasoideDegrees;
    @Column(name = "degrees_ferengi")
    private Integer ferengiDegrees;
    @Column(name = "peak_rainy_day")
    private Boolean peakRainyDay = Boolean.FALSE;

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

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getTrianglePerimeter() {
        return trianglePerimeter;
    }

    public void setTrianglePerimeter(Integer trianglePerimeter) {
        this.trianglePerimeter = trianglePerimeter;
    }

    public Integer getTriangleArea() {
        return triangleArea;
    }

    public void setTriangleArea(Integer triangleArea) {
        this.triangleArea = triangleArea;
    }

    public Integer getVulcanoDegrees() {
        return vulcanoDegrees;
    }

    public void setVulcanoDegrees(Integer vulcanoDegrees) {
        this.vulcanoDegrees = vulcanoDegrees;
    }

    public Integer getBetasoideDegrees() {
        return betasoideDegrees;
    }

    public void setBetasoideDegrees(Integer betasoideDegrees) {
        this.betasoideDegrees = betasoideDegrees;
    }

    public Integer getFerengiDegrees() {
        return ferengiDegrees;
    }

    public void setFerengiDegrees(Integer ferengiDegrees) {
        this.ferengiDegrees = ferengiDegrees;
    }

    public Boolean getPeakRainyDay() {
        return peakRainyDay;
    }

    public void setPeakRainyDay(Boolean peakRainyDay) {
        this.peakRainyDay = peakRainyDay;
    }

}
