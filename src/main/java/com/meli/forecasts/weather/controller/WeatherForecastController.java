package com.meli.forecasts.weather.controller;

import com.meli.forecasts.weather.dto.Configurations;
import com.meli.forecasts.weather.dto.WeatherEnum;
import com.meli.forecasts.weather.dto.response.DailyForecastResponse;
import com.meli.forecasts.weather.dto.response.DailyForecastSummaryResponse;
import com.meli.forecasts.weather.dto.response.ResponseDto;
import com.meli.forecasts.weather.model.DailyForecast;
import com.meli.forecasts.weather.service.WeatherForecastService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
public class WeatherForecastController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeatherForecastController.class);

    private WeatherForecastService service;
    private ModelMapper modelMapper;

    public WeatherForecastController(WeatherForecastService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/configurations")
    public ResponseEntity<Configurations> retrieveConfiguration() {
        return ResponseEntity.ok(new Configurations());
    }

    @PutMapping("/forecasts")
    public ResponseEntity<ResponseDto> calculateForecasts() {
        service.calculateAndSaveForecasts();
        return ResponseEntity.ok(new ResponseDto("Success calculating forecasts."));
    }

    @GetMapping("/forecasts")
    public ResponseEntity<List<DailyForecastResponse>> retrieveAllForecasts() {
        List<DailyForecast> forecasts = service.findAllDailyForecasts();
        if(CollectionUtils.isEmpty(forecasts)){
            log.warn("No weather forecast found.");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(forecasts.stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping(path = "/forecasts/day/{day}")
    public ResponseEntity<DailyForecastResponse> retrieveForecastForDay(@PathVariable Integer day) {
        Optional<DailyForecast> forecast = service.findForecastByDay(day);
        if (forecast.isPresent()) {
            return ResponseEntity.ok(convertToDto(forecast.get()));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/forecasts/summary")
    public ResponseEntity<Map<WeatherEnum, Map<Integer, List<DailyForecastSummaryResponse>>>> retrieveForecastSummary() {
        Map<WeatherEnum, Map<Integer, List<DailyForecastSummaryResponse>>> map = service.findSeasonForecast();
        if(map.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(map);
    }

    private DailyForecastResponse convertToDto(DailyForecast dailyForecast) {
        return modelMapper.map(dailyForecast, DailyForecastResponse.class);
    }

}
