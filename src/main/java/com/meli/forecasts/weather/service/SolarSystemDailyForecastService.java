package com.meli.forecasts.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.forecasts.weather.helper.AreaHelper;
import com.meli.forecasts.weather.helper.CoordinateHelper;
import com.meli.forecasts.weather.model.SolarSystemDailyForecast;
import com.meli.forecasts.weather.model.area.QuadrilateralArea;
import com.meli.forecasts.weather.model.area.TriangleArea;
import com.meli.forecasts.weather.model.coordinate.polar.SolarSystemDailyPolarCoordinate;
import com.meli.forecasts.weather.model.coordinate.cartesian.SolarSystemDailyCartesianCoordinate;
import com.meli.forecasts.weather.model.coordinate.cartesian.SunCartesianCoordinate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.meli.forecasts.weather.dto.PlanetEnum.BETASOIDE;
import static com.meli.forecasts.weather.dto.PlanetEnum.FERENGI;
import static com.meli.forecasts.weather.dto.PlanetEnum.VULCANO;
import static java.lang.String.format;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Service
public class SolarSystemDailyForecastService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SolarSystemDailyForecastService.class);

    private static SunCartesianCoordinate sunCartesianCoordinate = new SunCartesianCoordinate();

    @Value("${app.forecast.years}")
    public int FORECASTING_YEARS;
    public static final int DAYS_PER_DEGREE = 1;
    public static final int DEGREES_PER_REVOLUTION = 360;
    public static final int CLOCKWISE = -1;
    public static final int COUNTERWISE = 1;

    public void printForecasts(List<SolarSystemDailyForecast> forecasts) {

        forecasts.forEach(forecast -> log.info("Solar System daily forecast.", keyValue("dailyForecast", forecast)));

        log.info(format("sun inside %s - planets aligned %s",
                                  forecasts.stream().filter(forecast -> forecast.isSunInside()).count(),
                                  forecasts.stream().filter(forecast -> forecast.isPlanetsAligned()).count())
        );
    }

    public List<SolarSystemDailyForecast> generateForecasts() {
        List<SolarSystemDailyForecast> forecasts = new ArrayList<>();
        int numberOfDays = this.getNumberOfDays();

        for(int day = 1; day <= numberOfDays; day++){
            SolarSystemDailyCartesianCoordinate solarSystemCoordinate = CoordinateHelper.transform(new SolarSystemDailyPolarCoordinate(day));

            double triangleArea = AreaHelper.getArea(new TriangleArea(
                    solarSystemCoordinate.getFerengiCoordinate(),
                    solarSystemCoordinate.getVulcanoCoordinate(),
                    solarSystemCoordinate.getBetasoideCoordinate()
            ));

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

            SolarSystemDailyForecast forecast = new SolarSystemDailyForecast();
            forecast.setDay(day);
            forecast.setPlanetsAligned(triangleArea < 1);
            forecast.setSunInside(triangleArea >= quadrilateralArea);

            forecasts.add(forecast);
        }
        return forecasts;
    }

    private int getNumberOfDays(){
        int ferengiDaysPerYear = DEGREES_PER_REVOLUTION / FERENGI.getDegreesPerDay();
        int betasoideDaysPerYear = DEGREES_PER_REVOLUTION / BETASOIDE.getDegreesPerDay();
        int vulcanoDaysPerYear = DEGREES_PER_REVOLUTION / VULCANO.getDegreesPerDay();

        return Math.max(Math.max(ferengiDaysPerYear, betasoideDaysPerYear), vulcanoDaysPerYear) * FORECASTING_YEARS;
    }

}
