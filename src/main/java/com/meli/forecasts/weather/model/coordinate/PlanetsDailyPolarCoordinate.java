package com.meli.forecasts.weather.model.coordinate;

import com.meli.forecasts.weather.dto.PlanetEnum;
import com.meli.forecasts.weather.model.area.QuadrilateralArea;
import com.meli.forecasts.weather.model.area.TriangleArea;

import static com.meli.forecasts.weather.service.PlanetsDailyPositionFactory.DAY_PER_DEGREE;

public class PlanetsDailyPolarCoordinate {

    private static SunCartesianCoordinate sunCartesianCoordinate = new SunCartesianCoordinate();

    private int day;
    private int ferengiDegreesPosition;
    private int vulcanoDegreesPosition;
    private int betasoideDegreesPosition;

    public PlanetsDailyPolarCoordinate(
            int day,
            int ferengiDegreesPosition,
            int vulcanoDegreesPosition,
            int betasoideDegreesPosition) {
        this.day = day;
        this.ferengiDegreesPosition = ferengiDegreesPosition;
        this.vulcanoDegreesPosition = vulcanoDegreesPosition;
        this.betasoideDegreesPosition = betasoideDegreesPosition;
    }

    public SolarSystemDailyCartesianCoordinate transform() {
        SolarSystemDailyCartesianCoordinate cartesianCoordinate = new SolarSystemDailyCartesianCoordinate(day);

        final double ferengiRadiansPosition = Math.toRadians(ferengiDegreesPosition);
        FerengiCartesianCoordinate ferengiCartesianCoordinate = new FerengiCartesianCoordinate(
                PlanetEnum.FERENGI.getDistanceInMeters() * Math.cos(ferengiRadiansPosition),
                PlanetEnum.FERENGI.getDistanceInMeters() * Math.sin(ferengiRadiansPosition)
        );
        cartesianCoordinate.setFerengiCoordinate(ferengiCartesianCoordinate);

        final double betasoideRadiansPosition = Math.toRadians(betasoideDegreesPosition);
        BetasoideCartesianCoordinate betasoideCartesianCoordinate = new BetasoideCartesianCoordinate(
                PlanetEnum.BETASOIDE.getDistanceInMeters() * Math.cos(betasoideRadiansPosition),
                PlanetEnum.BETASOIDE.getDistanceInMeters() * Math.sin(betasoideRadiansPosition)
        );
        cartesianCoordinate.setBetasoideCoordinate(betasoideCartesianCoordinate);

        final double vulcanoRadiansPosition = Math.toRadians(vulcanoDegreesPosition);
        VulcanoCartesianCoordinate vulcanoCartesianCoordinate = new VulcanoCartesianCoordinate(
                PlanetEnum.VULCANO.getDistanceInMeters() * Math.cos(vulcanoRadiansPosition),
                PlanetEnum.VULCANO.getDistanceInMeters() * Math.sin(vulcanoRadiansPosition)
        );
        cartesianCoordinate.setVulcanoCoordinate(vulcanoCartesianCoordinate);

        TriangleArea triangleArea = new TriangleArea(
                ferengiCartesianCoordinate,
                vulcanoCartesianCoordinate,
                betasoideCartesianCoordinate
        );

        QuadrilateralArea quadrilateralArea = new QuadrilateralArea(
                new TriangleArea(
                        ferengiCartesianCoordinate,
                        vulcanoCartesianCoordinate,
                        sunCartesianCoordinate
                ),
                new TriangleArea(
                        sunCartesianCoordinate,
                        ferengiCartesianCoordinate,
                        betasoideCartesianCoordinate
                )
        );

        cartesianCoordinate.setSunInside(quadrilateralArea.getArea() < triangleArea.getArea());
        cartesianCoordinate.setAlignedPlanets(triangleArea.getArea() < 1);

        return cartesianCoordinate;
    }

}
