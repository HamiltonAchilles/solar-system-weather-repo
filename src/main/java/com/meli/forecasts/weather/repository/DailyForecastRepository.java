package com.meli.forecasts.weather.repository;

import com.meli.forecasts.weather.model.DailyForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyForecastRepository extends JpaRepository<DailyForecast, Integer> {

    List<DailyForecast> findAllOrderByDayAsc();

}
