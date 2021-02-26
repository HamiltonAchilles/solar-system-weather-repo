package com.meli.forecasts.weather.helper;

import com.meli.forecasts.weather.dto.area.QuadrilateralArea;
import com.meli.forecasts.weather.dto.area.TriangleArea;

import java.math.BigDecimal;
import java.math.MathContext;

public class AreaHelper {

    private static final int SCALE = 12;

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

    public static double getPerimeter(TriangleArea triangleArea) {
        double xA = triangleArea.getCoordinateA().getX();
        double xB = triangleArea.getCoordinateB().getX();
        double xC = triangleArea.getCoordinateC().getX();

        double yA = triangleArea.getCoordinateA().getY();
        double yB = triangleArea.getCoordinateB().getY();
        double yC = triangleArea.getCoordinateC().getY();

        double distanceAB = sqrt(
                pow2(xB - xA) + pow2(yB - yA)
        );
        double distanceBC = sqrt(
                pow2(xC - xB) + pow2(yC - yB)
        );
        double distanceCA = sqrt(
                pow2(xC - xA) + pow2(yC - yA)
        );
        return distanceAB + distanceBC + distanceCA;
    }

    private static double sqrt(double val) {
        return new BigDecimal(val).round(MathContext.DECIMAL64).setScale(SCALE).sqrt(MathContext.DECIMAL64).doubleValue();
    }

    private static double pow2(double val) {
        if(val < 1){
            return 0.0;
        }
        return new BigDecimal(val).round(MathContext.DECIMAL64).setScale(SCALE).pow(2).round(MathContext.DECIMAL64).doubleValue();
    }

}
