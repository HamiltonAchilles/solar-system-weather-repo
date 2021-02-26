package com.meli.forecasts.weather.service;

import com.meli.forecasts.weather.dto.SolarSystemDailyConfiguration;
import com.meli.forecasts.weather.model.SolarSystemDailyForecast;
import com.meli.forecasts.weather.repository.SolarSystemDailyForecastRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolarSystemDailyForecastService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SolarSystemDailyForecastService.class);

    private SolarSystemConfigurationService service;
    private List<SolarSystemDailyForecast> forecasts;
    private SolarSystemDailyForecastRepository repository;

    public SolarSystemDailyForecastService(
            SolarSystemConfigurationService service,
            SolarSystemDailyForecastRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    public List<SolarSystemDailyForecast> getAllForecasts(){
        return repository.findAll();
    }

    public void generateForecasts() {
        List<SolarSystemDailyConfiguration> configurations = service.generateConfigurations();
        forecasts = configurations.stream().map(SolarSystemDailyConfiguration::toForecast).collect(Collectors.toList());
        repository.saveAll(forecasts);
    }

    public Optional<SolarSystemDailyForecast> getForecastByDay(Integer day) {
        return repository.findById(day);
    }

}
