package com.meli.forecasts.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.forecasts.weather.dto.PlanetEnum;
import com.meli.forecasts.weather.model.coordinate.PlanetsDailyPolarCoordinate;
import com.meli.forecasts.weather.model.coordinate.SolarSystemDailyCartesianCoordinate;

import static java.lang.String.format;

public class PlanetsDailyPositionFactory {

    public static final int DAY_PER_DEGREE = 1;
    public static final double DEGREES_PER_REVOLUTION = 360.0;
    public static final int FORECASTING_YEARS = 1; //TODO change from 1 to 10

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {

        int sunInside = 0;
        int planetsAligned = 0;

        int degree = 0;
        while(PlanetEnum.FERENGI.getAngularSpeedInDegrees() / DEGREES_PER_REVOLUTION * degree < FORECASTING_YEARS
                || PlanetEnum.VULCANO.getAngularSpeedInDegrees() / DEGREES_PER_REVOLUTION * degree < FORECASTING_YEARS
                || PlanetEnum.BETASOIDE.getAngularSpeedInDegrees() / DEGREES_PER_REVOLUTION * degree < FORECASTING_YEARS){

            degree = degree + 1;
            PlanetsDailyPolarCoordinate polarCoordinate = new PlanetsDailyPolarCoordinate(
                    DAY_PER_DEGREE * degree,
                    degree * PlanetEnum.FERENGI.getAngularSpeedInDegrees() * PlanetEnum.FERENGI.getClockwise(),
                    degree * PlanetEnum.VULCANO.getAngularSpeedInDegrees() * PlanetEnum.VULCANO.getClockwise(),
                    degree * PlanetEnum.BETASOIDE.getAngularSpeedInDegrees() * PlanetEnum.BETASOIDE.getClockwise()
            );
            SolarSystemDailyCartesianCoordinate solarSystemDailyCartesianCoordinate = polarCoordinate.transform();
            System.out.println(objectMapper.writeValueAsString(solarSystemDailyCartesianCoordinate));
            if(solarSystemDailyCartesianCoordinate.isSunInside()) {
                sunInside++;
            }
            if(solarSystemDailyCartesianCoordinate.isAlignedPlanets()){
                planetsAligned++;
            }
        }
        System.out.println(PlanetEnum.FERENGI.getAngularSpeedInDegrees() / 360.0 * degree);
        System.out.println(PlanetEnum.VULCANO.getAngularSpeedInDegrees() / 360.0 * degree);
        System.out.println(PlanetEnum.BETASOIDE.getAngularSpeedInDegrees() / 360.0 * degree);
        System.out.println(format("sun inside %s - planets aligned %s", sunInside, planetsAligned));

    }
}
