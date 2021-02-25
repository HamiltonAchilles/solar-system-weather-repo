package com.meli.forecasts.weather.model.coordinate.polar;

import com.meli.forecasts.weather.dto.PlanetEnum;

public class FerengiPolarCoordinate extends PolarCoordinate {

    public FerengiPolarCoordinate(double degrees) {
        super(Math.toRadians(degrees), PlanetEnum.FERENGI.getRadiusInMeters());
    }

}
