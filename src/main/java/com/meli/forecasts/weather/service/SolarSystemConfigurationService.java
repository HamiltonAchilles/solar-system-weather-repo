package com.meli.forecasts.weather.service;

import com.meli.forecasts.weather.helper.AreaHelper;
import com.meli.forecasts.weather.helper.CoordinateHelper;
import com.meli.forecasts.weather.dto.SolarSystemDailyConfiguration;
import com.meli.forecasts.weather.dto.area.QuadrilateralArea;
import com.meli.forecasts.weather.dto.area.TriangleArea;
import com.meli.forecasts.weather.dto.coordinate.polar.SolarSystemDailyPolarCoordinate;
import com.meli.forecasts.weather.dto.coordinate.cartesian.SolarSystemDailyCartesianCoordinate;
import com.meli.forecasts.weather.dto.coordinate.cartesian.SunCartesianCoordinate;
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
public class SolarSystemConfigurationService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SolarSystemConfigurationService.class);

    private static SunCartesianCoordinate sunCartesianCoordinate = new SunCartesianCoordinate();

    @Value("${app.forecast.years}")
    public int FORECASTING_YEARS;
    public static final int DAYS_PER_DEGREE = 1;
    public static final int DEGREES_PER_REVOLUTION = 360;
    public static final int CLOCKWISE = -1;
    public static final int COUNTERWISE = 1;

    public List<SolarSystemDailyConfiguration> generateConfigurations() {
        List<SolarSystemDailyConfiguration> configurations = new ArrayList<>();
        int numberOfDays = this.getNumberOfDays();

        for(int day = 1; day <= numberOfDays; day++){
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

            SolarSystemDailyConfiguration configuration = new SolarSystemDailyConfiguration();
            configuration.setDay(day);
            configuration.setPlanetsAligned(triangleArea < 1);
            configuration.setSunInside(triangleArea >= quadrilateralArea);
            configuration.setTrianglePerimeter(AreaHelper.getPerimeter(triangle));

            configurations.add(configuration);
        }
        return configurations;
    }

    private int getNumberOfDays(){
        int ferengiDaysPerYear = DEGREES_PER_REVOLUTION / FERENGI.getDegreesPerDay();
        int betasoideDaysPerYear = DEGREES_PER_REVOLUTION / BETASOIDE.getDegreesPerDay();
        int vulcanoDaysPerYear = DEGREES_PER_REVOLUTION / VULCANO.getDegreesPerDay();

        return Math.max(Math.max(ferengiDaysPerYear, betasoideDaysPerYear), vulcanoDaysPerYear) * FORECASTING_YEARS;
    }

}
