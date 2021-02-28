package com.meli.forecasts.weather.helper;

import com.meli.forecasts.weather.dto.area.QuadrilateralArea;
import com.meli.forecasts.weather.dto.area.TriangleArea;

import java.math.BigDecimal;
import java.math.MathContext;

public class AreaHelper {

    private static final long AREA_NEAR_ZERO = 30000;
    private static final int SCALE = 3;

    public static double getArea(TriangleArea triangle) {
        double xA = triangle.getCoordinateA().getX();
        double xB = triangle.getCoordinateB().getX();
        double xC = triangle.getCoordinateC().getX();

        double yA = triangle.getCoordinateA().getY();
        double yB = triangle.getCoordinateB().getY();
        double yC = triangle.getCoordinateC().getY();

        return 0.5 * Math.abs(
                            multiply(xA, yB) +
                            multiply(xC, yA) +
                            multiply(xB, yC) -
                            multiply(xC, yB) -
                            multiply(xA, yC) -
                            multiply(xB, yA)
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
                pow2(xA - xC) + pow2(yA - yC)
        );
        return distanceAB + distanceBC + distanceCA;
    }

    private static double sqrt(double val) {
        return new BigDecimal(val).round(MathContext.DECIMAL32).setScale(8).sqrt(MathContext.DECIMAL32).doubleValue();
    }

    private static double pow2(double val) {
        if(Math.abs(val) < 1){
            return 0.0;
        }
        return new BigDecimal(val).round(MathContext.DECIMAL32).setScale(8).pow(2).round(MathContext.DECIMAL32).doubleValue();
    }

    private static double multiply(double valA, double valB){
        return valA * valB;
    }

    public static boolean isAligned(TriangleArea triangle) {
        return getArea(triangle) < AREA_NEAR_ZERO;
    }

}
