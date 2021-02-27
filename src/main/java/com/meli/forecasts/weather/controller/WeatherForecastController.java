package com.meli.forecasts.weather.controller;

import com.meli.forecasts.weather.dto.response.ResponseDto;
import com.meli.forecasts.weather.dto.response.DailyForecastResponse;
import com.meli.forecasts.weather.model.DailyForecast;
import com.meli.forecasts.weather.service.WeatherForecastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.meli.forecasts.weather.dto.WeatherEnum.UNKNOWN;
import static java.text.MessageFormat.format;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

@RestController
@RequestMapping("/weather")
public class WeatherForecastController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeatherForecastController.class);

    private WeatherForecastService service;

    public WeatherForecastController(WeatherForecastService service) {
        this.service = service;
    }

    @PutMapping("/forecasts")
    public ResponseEntity<ResponseDto> calculateForecasts() {
        service.generateForecasts();
        return ResponseEntity.ok(new ResponseDto("Success generating forecasts."));
    }

    @GetMapping("/forecasts")
    public ResponseEntity<List<DailyForecast>> retrieveAllForecasts() {
        List<DailyForecast> forecasts = service.getAllDailyForecasts();
        return ResponseEntity.ok(forecasts);
    }

    @GetMapping(path = "/forecasts/day/{day}")
    public ResponseEntity<DailyForecastResponse> retrieveForecastForDay(@PathVariable Integer day) {
        Optional<DailyForecast> forecast = service.getForecastByDay(day);
        if(forecast.isPresent()) {
            final String message = format("Forecast founded for day {0}.", day);
            log.info(message, day, keyValue("forecast", forecast.get()));
            return ResponseEntity.ok(new DailyForecastResponse(forecast.get().getDay(), forecast.get().getWeather(), message));
        } else {
            final String errorMessage = format("No weather forecast founded for day {0}.", day);
            log.info(errorMessage);
            return ResponseEntity.ok(new DailyForecastResponse(day, UNKNOWN, errorMessage));
        }
    }
}
