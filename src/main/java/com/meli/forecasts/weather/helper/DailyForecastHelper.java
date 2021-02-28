package com.meli.forecasts.weather.helper;

import com.meli.forecasts.weather.dto.WeatherEnum;
import com.meli.forecasts.weather.dto.area.QuadrilateralArea;
import com.meli.forecasts.weather.dto.area.TriangleArea;
import com.meli.forecasts.weather.dto.coordinate.cartesian.SolarSystemDailyCartesianCoordinate;
import com.meli.forecasts.weather.dto.coordinate.cartesian.SunCartesianCoordinate;
import com.meli.forecasts.weather.dto.coordinate.polar.SolarSystemDailyPolarCoordinate;
import com.meli.forecasts.weather.model.DailyForecast;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.meli.forecasts.weather.dto.PlanetEnum.BETASOIDE;
import static com.meli.forecasts.weather.dto.PlanetEnum.FERENGI;
import static com.meli.forecasts.weather.dto.PlanetEnum.VULCANO;
import static com.meli.forecasts.weather.dto.WeatherEnum.CONDICIONES_NORMALES;
import static com.meli.forecasts.weather.dto.WeatherEnum.CONDICIONES_OPTIMAS;
import static com.meli.forecasts.weather.dto.WeatherEnum.LLUVIA;
import static com.meli.forecasts.weather.dto.WeatherEnum.SEQUIA;
import static java.text.MessageFormat.format;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Component
public class DailyForecastHelper {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DailyForecastHelper.class);

    private static final int DEGREES_PER_REVOLUTION = 360;
    private SunCartesianCoordinate sunCartesianCoordinate;

    public DailyForecastHelper(SunCartesianCoordinate sunCartesianCoordinate) {
        this.sunCartesianCoordinate = sunCartesianCoordinate;
    }

    public List<DailyForecast> calculateForecasts(int forecastingYears) {
        log.info(format("Weather Forecast calculation requested for {0} year(s)", forecastingYears));

        WeatherEnum lastWeather = WeatherEnum.UNKNOWN;
        int numberOfDays = getMaxNumberOfDaysPerYear() * forecastingYears;
        List<DailyForecast> forecasts = new ArrayList<>(numberOfDays);
        for (int day = 1, season = 0; day <= numberOfDays; day++) {
            SolarSystemDailyCartesianCoordinate solarSystemCoordinate = CoordinateHelper.transform(new SolarSystemDailyPolarCoordinate(day));
            TriangleArea triangle = new TriangleArea(
                    solarSystemCoordinate.getFerengiCoordinate(),
                    solarSystemCoordinate.getVulcanoCoordinate(),
                    solarSystemCoordinate.getBetasoideCoordinate()
            );
            double triangleArea = AreaHelper.getArea(triangle);
            /*double quadrilateralArea = AreaHelper.getArea(new QuadrilateralArea(
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
            ));*/
            boolean isPlanetAligned = AreaHelper.isAligned(triangle);
            //boolean isSunInside = quadrilateralArea < AREA_NEAR_ZERO || triangleArea >= quadrilateralArea;
            boolean isSunInside;
            if(isPlanetAligned){
                isSunInside = AreaHelper.isAligned(new TriangleArea(
                        solarSystemCoordinate.getFerengiCoordinate(),
                        solarSystemCoordinate.getVulcanoCoordinate(),
                        sunCartesianCoordinate
                ));
            } else {
                Polygon polygon = new Polygon(triangle.getXAxis(), triangle.getYAxis(), 3);
                isSunInside = polygon.contains(0, 0);
            }

            DailyForecast forecast = new DailyForecast();
            forecast.setDay(day);
            forecast.setTrianglePerimeter(AreaHelper.getPerimeter(triangle));
            forecast.setTriangleArea(triangleArea);
            //forecast.setQuadrilateralArea(quadrilateralArea);

            int daysInVulcano = -1 * (day * VULCANO.getDegreesPerDay());
            int daysInFerengi = day * FERENGI.getDegreesPerDay();
            int daysInBetasoide = day * BETASOIDE.getDegreesPerDay();

            forecast.setVulcanoDegrees(360 + (-1 * (daysInVulcano <= 360 ? daysInVulcano : daysInVulcano % 360)));
            forecast.setFerengiDegrees(daysInFerengi <= 360 ? daysInFerengi : daysInFerengi % 360);
            forecast.setBetasoideDegrees(daysInBetasoide <= 360 ? daysInBetasoide : daysInBetasoide % 360);

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
            log.info(format("Weather Forecast calculated for day {0}", day), keyValue("forecast", forecast));
        }
        return forecasts;
    }

    private int getMaxNumberOfDaysPerYear() {
        int ferengiDaysPerYear = Math.abs(DEGREES_PER_REVOLUTION / FERENGI.getDegreesPerDay());
        int betasoideDaysPerYear = Math.abs(DEGREES_PER_REVOLUTION / BETASOIDE.getDegreesPerDay());
        int vulcanoDaysPerYear = Math.abs(DEGREES_PER_REVOLUTION / VULCANO.getDegreesPerDay());
        return Math.max(Math.max(ferengiDaysPerYear, betasoideDaysPerYear), vulcanoDaysPerYear);
    }

}
