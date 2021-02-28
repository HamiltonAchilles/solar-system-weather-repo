package com.meli.forecasts.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Configurations {

    @JsonProperty("planetas")
    private PlanetEnum[] planets = PlanetEnum.values();
    @JsonProperty("climas")
    private WeatherEnum[] weathers = WeatherEnum.values();

    public PlanetEnum[] getPlanets() {
        return planets;
    }

    public void setPlanets(PlanetEnum[] planets) {
        this.planets = planets;
    }

    public WeatherEnum[] getWeathers() {
        return weathers;
    }

    public void setWeathers(WeatherEnum[] weathers) {
        this.weathers = weathers;
    }

}
