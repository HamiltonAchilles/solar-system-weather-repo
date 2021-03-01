package com.meli.forecasts.weather.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Season {

    @JsonProperty("periodo")
    private int season;
    @JsonProperty("duracion_periodo_dias")
    private int numberOfDays;
    @JsonProperty("pico_lluvia_dia")
    private Integer peakRainyDay;

    public Season(int season, int numberOfDays, Integer peakRainyDay) {
        this.season = season;
        this.numberOfDays = numberOfDays;
        this.peakRainyDay = peakRainyDay;
    }

    public Season(int season, int numberOfDays) {
        this.season = season;
        this.numberOfDays = numberOfDays;
    }

    public Integer getPeakRainyDay() {
        return peakRainyDay;
    }

    public void setPeakRainyDay(Integer peakRainyDay) {
        this.peakRainyDay = peakRainyDay;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

}
