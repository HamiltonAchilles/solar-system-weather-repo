package com.meli.forecasts.weather.helper;

import com.meli.forecasts.weather.model.area.QuadrilateralArea;
import com.meli.forecasts.weather.model.area.TriangleArea;

public class AreaHelper {

    public static double getArea(TriangleArea triangle) {
        return 0.5 * Math.abs(
                (triangle.getCoordinateA().getX() * triangle.getCoordinateB().getY()) +
                (triangle.getCoordinateC().getX() * triangle.getCoordinateA().getY()) +
                (triangle.getCoordinateB().getX() * triangle.getCoordinateC().getY()) -
                (triangle.getCoordinateC().getX() * triangle.getCoordinateB().getY()) -
                (triangle.getCoordinateA().getX() * triangle.getCoordinateC().getY()) -
                (triangle.getCoordinateB().getX() * triangle.getCoordinateA().getY())
        );
    }

    public static double getArea(QuadrilateralArea quadrilateralArea) {
        return getArea(quadrilateralArea.getTriangleAreaOne()) + getArea(quadrilateralArea.getTriangleAreaTwo());
    }

}
