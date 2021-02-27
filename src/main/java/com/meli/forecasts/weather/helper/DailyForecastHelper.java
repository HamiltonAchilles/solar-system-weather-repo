package com.meli.forecasts.weather.helper;

import com.meli.forecasts.weather.dto.WeatherEnum;
import com.meli.forecasts.weather.dto.area.QuadrilateralArea;
import com.meli.forecasts.weather.dto.area.TriangleArea;
import com.meli.forecasts.weather.dto.coordinate.cartesian.SolarSystemDailyCartesianCoordinate;
import com.meli.forecasts.weather.dto.coordinate.cartesian.SunCartesianCoordinate;
import com.meli.forecasts.weather.dto.coordinate.polar.SolarSystemDailyPolarCoordinate;
import com.meli.forecasts.weather.model.DailyForecast;

import java.util.ArrayList;
import java.util.List;

import static com.meli.forecasts.weather.dto.PlanetEnum.BETASOIDE;
import static com.meli.forecasts.weather.dto.PlanetEnum.FERENGI;
import static com.meli.forecasts.weather.dto.PlanetEnum.VULCANO;
import static com.meli.forecasts.weather.dto.WeatherEnum.CONDICIONES_NORMALES;
import static com.meli.forecasts.weather.dto.WeatherEnum.CONDICIONES_OPTIMAS;
import static com.meli.forecasts.weather.dto.WeatherEnum.LLUVIA;
import static com.meli.forecasts.weather.dto.WeatherEnum.SEQUIA;

public class DailyForecastHelper {

    private static SunCartesianCoordinate sunCartesianCoordinate = new SunCartesianCoordinate();
    private static final int AREA_CONSIDERED_AS_ZERO = 1;
    private static final int DEGREES_PER_REVOLUTION = 360;

    public static List<DailyForecast> calculateForecasts(int forecastingYears) {
        List<DailyForecast> forecasts = new ArrayList<>();
        int numberOfDays = getNumberOfDays() * forecastingYears;
        WeatherEnum lastWeather = WeatherEnum.UNKNOWN;
        for (int day = 1, season = 0; day <= numberOfDays; day++) {
            SolarSystemDailyCartesianCoordinate solarSystemCoordinate = CoordinateHelper.transform(new SolarSystemDailyPolarCoordinate(day));
            TriangleArea triangle = new TriangleArea(
                    solarSystemCoordinate.getFerengiCoordinate(),
                    solarSystemCoordinate.getVulcanoCoordinate(),
                    solarSystemCoordinate.getBetasoideCoordinate()
            );
            double triangleArea = AreaHelper.getArea(triangle);
            double quadrilateralArea = AreaHelper.getArea(new QuadrilateralArea(
                    new TriangleArea(
                            solarSystemCoordinate.getVulcanoCoordinate(),
                            solarSystemCoordinate.getFerengiCoordinate(),
                            sunCartesianCoordinate
                    ),
                    new TriangleArea(
                            solarSystemCoordinate.getBetasoideCoordinate(),
                            solarSystemCoordinate.getFerengiCoordinate(),
                            sunCartesianCoordinate
                    )
            ));
            boolean isPlanetAligned = triangleArea < AREA_CONSIDERED_AS_ZERO;
            boolean isSunInside = triangleArea >= quadrilateralArea;
            DailyForecast forecast = new DailyForecast();
            forecast.setDay(day);
            forecast.setTrianglePerimeter(AreaHelper.getPerimeter(triangle));
            if (isPlanetAligned && isSunInside) {
                forecast.setWeather(SEQUIA);
            }
            if (!isPlanetAligned && isSunInside) {
                forecast.setWeather(LLUVIA);
            }
            if (!isPlanetAligned && !isSunInside) {
                forecast.setWeather(CONDICIONES_NORMALES);
            }
            if (isPlanetAligned && !isSunInside) {
                forecast.setWeather(CONDICIONES_OPTIMAS);
            }
            if (forecast.getWeather() != lastWeather) {
                season = season + 1;
                lastWeather = forecast.getWeather();
            }
            forecast.setSeason(season);
            forecasts.add(forecast);
        }
        return forecasts;
    }

    private static int getNumberOfDays() {
        int ferengiDaysPerYear = DEGREES_PER_REVOLUTION / FERENGI.getDegreesPerDay();
        int betasoideDaysPerYear = DEGREES_PER_REVOLUTION / BETASOIDE.getDegreesPerDay();
        int vulcanoDaysPerYear = DEGREES_PER_REVOLUTION / VULCANO.getDegreesPerDay();
        return Math.max(Math.max(ferengiDaysPerYear, betasoideDaysPerYear), vulcanoDaysPerYear);
    }

}
