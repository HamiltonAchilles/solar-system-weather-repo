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
public class DailyForecast {

    @Id
    private int day;
    @Enumerated(EnumType.STRING)
    @Column(name = "weather")
    private WeatherEnum weather;
    @Column(name = "season")
    private int season;
    @Column(name = "triangle_perimeter")
    private double trianglePerimeter;
    @Column(name = "triangle_area")
    private double triangleArea;
    @Column(name = "quadrilateral_area")
    private double quadrilateralArea;
    @Column(name = "degrees_vulcano")
    private int vulcanoDegrees;
    @Column(name = "degrees_betasoide")
    private int betasoideDegrees;
    @Column(name = "degrees_ferengi")
    private int ferengiDegrees;


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

    public double getTriangleArea() {
        return triangleArea;
    }

    public void setTriangleArea(double triangleArea) {
        this.triangleArea = triangleArea;
    }

    public double getQuadrilateralArea() {
        return quadrilateralArea;
    }

    public void setQuadrilateralArea(double quadrilateralArea) {
        this.quadrilateralArea = quadrilateralArea;
    }

    public int getVulcanoDegrees() {
        return vulcanoDegrees;
    }

    public void setVulcanoDegrees(int vulcanoDegrees) {
        this.vulcanoDegrees = vulcanoDegrees;
    }

    public int getBetasoideDegrees() {
        return betasoideDegrees;
    }

    public void setBetasoideDegrees(int betasoideDegrees) {
        this.betasoideDegrees = betasoideDegrees;
    }

    public int getFerengiDegrees() {
        return ferengiDegrees;
    }

    public void setFerengiDegrees(int ferengiDegrees) {
        this.ferengiDegrees = ferengiDegrees;
    }

}
