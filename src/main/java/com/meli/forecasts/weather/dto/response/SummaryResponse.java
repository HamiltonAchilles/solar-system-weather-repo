package com.meli.forecasts.weather.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.forecasts.weather.dto.WeatherEnum;

import java.util.List;

public class SummaryResponse {

    @JsonProperty("clima")
    private WeatherEnum weather;
    @JsonProperty("numero_de_periodos")
    private int numberOfSeasons;
    @JsonProperty("periodos")
    private List<Season> seasons;

    public WeatherEnum getWeather() {
        return weather;
    }

    public void setWeather(WeatherEnum weather) {
        this.weather = weather;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

}
