package com.meli.forecasts.weather.service;

import com.meli.forecasts.weather.model.DailyForecast;
import com.meli.forecasts.weather.repository.DailyForecastRepository;
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

    private SolarSystemConfigurationService service;
    private DailyForecastRepository dailyRepository;

    public WeatherForecastService(
            SolarSystemConfigurationService service,
            DailyForecastRepository dailyRepository) {
        this.service = service;
        this.dailyRepository = dailyRepository;
    }

    public List<DailyForecast> getAllDailyForecasts() {
        return dailyRepository.findAll();
    }

    public Optional<DailyForecast> getForecastByDay(Integer day) {
        return dailyRepository.findById(day);
    }

    @Transactional
    public void generateForecasts() {
        List<DailyForecast> forecasts = service.generateForecasts();
        dailyRepository.deleteAll();
        dailyRepository.saveAll(forecasts);
        Map<Integer, Optional<DailyForecast>> seasonsMap = forecasts.stream()
                                                                    .collect(groupingBy(
                                                                            DailyForecast::getSeason,
                                                                            maxBy(Comparator.comparingDouble(DailyForecast::getTrianglePerimeter))));
        seasonsMap.forEach((integer, dailyForecast) -> {
            if (dailyForecast.get().getWeather() == LLUVIA) {
                final DailyForecast forecast = dailyForecast.get();
                forecast.setWeather(LLUVIA_PICO_MAXIMO);
                dailyRepository.save(forecast);
            }
        });
    }

}
