package com.meli.forecasts.weather.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Season {

    @JsonProperty("periodo")
    private int season;
    @JsonProperty("duracion_periodo_dias")
    private int numberOfDays;
    @JsonProperty("dia")
    private List<DailyForecastSummaryResponse> dailyForecasts;

    public Season(int season, int numberOfDays, List<DailyForecastSummaryResponse> dailyForecasts) {
        this.numberOfDays = numberOfDays;
        this.dailyForecasts = dailyForecasts;
        this.season = season;
    }

    public List<DailyForecastSummaryResponse> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecastSummaryResponse> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
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
