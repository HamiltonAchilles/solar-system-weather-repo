package com.meli.forecasts.weather.service;

import com.meli.forecasts.weather.helper.DailyForecastHelper;
import com.meli.forecasts.weather.model.DailyForecast;
import com.meli.forecasts.weather.repository.DailyForecastRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.meli.forecasts.weather.dto.WeatherEnum.LLUVIA;
import static com.meli.forecasts.weather.dto.WeatherEnum.LLUVIA_PICO_MAXIMO;
import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Service
public class WeatherForecastService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeatherForecastService.class);

    @Value("${app.forecast.years}")
    private int FORECASTING_YEARS;
    private DailyForecastRepository repository;
    private DailyForecastHelper helper;

    public WeatherForecastService(
            DailyForecastRepository repository, DailyForecastHelper helper) {
        this.repository = repository;
        this.helper = helper;
    }

    public List<DailyForecast> findAllDailyForecasts() {
        return repository.findByOrderByDay();
    }

    public Optional<DailyForecast> findForecastByDay(Integer day) {
        Optional<DailyForecast> optional = repository.findById(day);
        if (optional.isPresent()) {
            log.info(format("Forecast found for day {0}.", day), day, keyValue("forecast", optional.get()));
        } else {
            log.info(format("Forecast not found for day {0}.", day));
        }
        return optional;
    }

    @Transactional
    public void calculateAndSaveForecasts() {
        List<DailyForecast> forecasts = helper.calculateForecasts(FORECASTING_YEARS);
        /*repository.deleteAll();
        log.debug("All previous forecasts deleted.");
        repository.saveAll(forecasts);
        log.debug("All new forecasts saved.");
        Map<Integer, Optional<DailyForecast>> seasonsMap = forecasts.stream()
                                                                    .collect(groupingBy(
                                                                            DailyForecast::getSeason,
                                                                            maxBy(Comparator.comparingDouble(DailyForecast::getTrianglePerimeter))));
        seasonsMap.forEach((integer, dailyForecast) -> {
            if (dailyForecast.get().getWeather() == LLUVIA) {
                final DailyForecast forecast = dailyForecast.get();
                forecast.setWeather(LLUVIA_PICO_MAXIMO);
                repository.save(forecast);
            }
        });
        log.debug("Forecasts updated regarding to 'LLUVIA_PICO_MAXIMO' weather.");*/
    }

}