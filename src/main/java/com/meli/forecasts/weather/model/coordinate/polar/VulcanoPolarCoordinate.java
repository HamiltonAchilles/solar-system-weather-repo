package com.meli.forecasts.weather.model.coordinate.polar;

import static com.meli.forecasts.weather.dto.PlanetEnum.VULCANO;

public class VulcanoPolarCoordinate extends PolarCoordinate {

    public VulcanoPolarCoordinate(double degrees) {
        super(Math.toRadians(degrees), VULCANO.getRadiusInMeters());
    }

}
