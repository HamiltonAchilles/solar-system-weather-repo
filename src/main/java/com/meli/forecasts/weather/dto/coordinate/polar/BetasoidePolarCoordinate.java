package com.meli.forecasts.weather.dto.coordinate.polar;

import com.meli.forecasts.weather.dto.PlanetEnum;

public class BetasoidePolarCoordinate extends PolarCoordinate {

    public BetasoidePolarCoordinate(double degrees) {
        super(Math.toRadians(degrees), PlanetEnum.BETASOIDE.getRadiusInMeters());
    }

}
