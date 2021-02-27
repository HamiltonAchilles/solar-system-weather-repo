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
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

@Service
public class WeatherForecastService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeatherForecastService.class);

    @Value("${app.forecast.years}")
    private int FORECASTING_YEARS;
    private DailyForecastRepository repository;

    public WeatherForecastService(
            DailyForecastRepository repository) {
        this.repository = repository;
    }

    public List<DailyForecast> findAllDailyForecasts() {
        return repository.findAllOrderByDayAsc();
    }

    public Optional<DailyForecast> findForecastByDay(Integer day) {
        return repository.findById(day);
    }

    @Transactional
    public void calculateAndSaveForecasts() {
        List<DailyForecast> forecasts = DailyForecastHelper.calculateForecasts(FORECASTING_YEARS);
        repository.deleteAll();
        repository.saveAll(forecasts);
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
    }

}