package com.meli.forecasts.weather.controller;

import com.meli.forecasts.weather.dto.PlanetEnum;
import com.meli.forecasts.weather.dto.WeatherDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.meli.forecasts.weather.dto.WeatherEnum.LLUVIA;

@RestController
@RequestMapping("/weather")
public class WeatherForecastController {

    @GetMapping(path = "/planet/{planet}/day/{day}")
    public ResponseEntity<WeatherDto> getPayment(@PathVariable PlanetEnum planet, @PathVariable Integer day) {
        WeatherDto weather = new WeatherDto(1, LLUVIA);
        return ResponseEntity.ok(weather);
    }
}
