package com.meli.forecasts.weather.helper;

import com.meli.forecasts.weather.model.coordinate.cartesian.CartesianCoordinate;
import com.meli.forecasts.weather.model.coordinate.polar.PolarCoordinate;
import com.meli.forecasts.weather.model.coordinate.cartesian.SolarSystemDailyCartesianCoordinate;
import com.meli.forecasts.weather.model.coordinate.polar.SolarSystemDailyPolarCoordinate;

public class CoordinateHelper {

    public static SolarSystemDailyCartesianCoordinate transform(SolarSystemDailyPolarCoordinate coordinate) {
        final SolarSystemDailyCartesianCoordinate cartesianCoordinate = new SolarSystemDailyCartesianCoordinate(coordinate.getDay());

        cartesianCoordinate.setFerengiCoordinate(transform(coordinate.getFerengiPolarCoordinate()));
        cartesianCoordinate.setBetasoideCoordinate(transform(coordinate.getBetasoidePolarCoordinate()));
        cartesianCoordinate.setVulcanoCoordinate(transform(coordinate.getVulcanoPolarCoordinate()));

        return cartesianCoordinate;
    }

    public static CartesianCoordinate transform(PolarCoordinate polar) {
        return new CartesianCoordinate(polar.getRadius() * Math.cos(polar.getRadians()), polar.getRadius() * Math.sin(polar.getRadians()));
    }
}
