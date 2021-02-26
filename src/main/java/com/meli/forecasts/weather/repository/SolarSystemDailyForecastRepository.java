package com.meli.forecasts.weather.repository;

import com.meli.forecasts.weather.model.SolarSystemDailyForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarSystemDailyForecastRepository extends JpaRepository<SolarSystemDailyForecast, Integer> {


}
